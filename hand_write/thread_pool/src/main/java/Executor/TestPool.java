package Executor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestPool {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(2,  1000, TimeUnit.MILLISECONDS, 10, (queue, task) -> {
            // 1. 死等
            // queue.put(task);
            // 2. 带超时等待
            // queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            // 3. 让调用者放弃任务执行
            // System.out.println("放弃" + task);
            // 4. 让调用者抛出异常
            // throw new RuntimeException("任务执行失败" + task);
            // 5. 让调用者自己执行任务
            task.run();
        });
        for (int i = 0; i < 4; i++) {
            int j = i;
            pool.execute(() -> System.out.println(j + " is running and done."));
        }
    }
}

// 拒绝策略
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

class ThreadPool {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;
    // 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;
    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();

    // 核心线程数
    private int coreSize;

    // 获取任务时的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    // 执行任务
    public void execute(Runnable task) {
        synchronized (workers) {
            // 当前有空闲线程
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                // 当前没有空闲线程
                //taskQueue.put(task);
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 当 task 不为空，执行 task
            // 当 task 执行完毕后，再接着从任务队列获取任务并执行
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
            }
        }
    }

}

class BlockingQueue<T> {
    // 1. 任务队列
    private Deque<T> queue = new ArrayDeque<>();

    // 2. 锁
    private ReentrantLock lock = new ReentrantLock();

    // 3. 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    // 4. 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    // 5. 容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                // 返回的是剩余的时间
                nanos = emptyWaitSet.awaitNanos(nanos);
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞获取
    public T take() {
        lock.lock();
        try {
            // 如果队列为空
            while (queue.isEmpty()) {
                try {
                    // 阻塞消费者
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 队列不为空，消费
            T task = queue.removeFirst();
            // 唤醒生产者
            fullWaitSet.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加
    public void put (T task) {
        lock.lock();
        try {
            // 如果队列已满
            while (queue.size() == capacity) {
                try {
                    // 阻塞生产者
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 队列未满，生产
            queue.addLast(task);
            // 唤醒消费者
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    // 带超市时间阻塞添加
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(timeout);
        lock.lock();
        try {
            while (queue.size() == capacity) {
                if (nanos <= 0) {
                    return false;
                }
                try {
                    // 返回的是剩余的时间
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    // 获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            // 如果队列已满
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                // 队列未满，生产
                queue.addLast(task);
                // 唤醒消费者
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
