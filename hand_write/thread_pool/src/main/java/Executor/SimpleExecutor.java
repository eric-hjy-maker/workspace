package Executor;

import javafx.concurrent.Worker;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @video_url https://www.bilibili.com/video/BV1jU4y1K7Tr/?spm_id_from=333.337.search-card.all.click&vd_source=b60fb1a1a8f06e334bf9ca2665cdd202
 * @author hjy
 **/
public class SimpleExecutor {

    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
    private AtomicInteger State = new AtomicInteger();
    private AtomicInteger workerCount = new AtomicInteger();
    private int finishedWorkerCount = 0;
    // 当前线程池的状态
    private final static int RUNNING = 0;
    private final static int STOPPED = 1;
    private BlockingQueue<Runnable> taskQueue = new PriorityBlockingQueue<>();
    private HashSet<Worker> workers = new HashSet<>();
    private final ReentrantLock L = new ReentrantLock();


    public SimpleExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime){
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    public void execute(Runnable task){
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        // 当前状态是否为 RUNNING
        if (State.get() == RUNNING) {
            // 当前线程池中的线程数小于 corePoolSize
            if (workerCount.get() < corePoolSize && addWorker(task, true)) {
                return;
            }
            if (workerCount.get() < maxPoolSize && addWorker(task, false)) {
                return;
            }
            L.lock();
            try {
                if (State.get() == RUNNING) {
                    if (!taskQueue.offer(task)) {
                        throw new RuntimeException("taskQueue is full");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                L.unlock();
            }
        } else {
            // 当前状态为 STOPPED, 直接抛出异常
            throw new IllegalStateException("线程池已经停止");
        }
    }

    public boolean addWorker(Runnable firstTask, boolean core){
        if (State.get() == STOPPED) {
            return false;
        }
        while (true) {
            if (State.get() == STOPPED) {
                return false;
            }
            while (true) {
                int count = workerCount.get();
                if (count >= (core ? corePoolSize : maxPoolSize)) {
                    return false;
                }
                if (workerCount.compareAndSet(count, count + 1)) {
                    break;
                }
            }
        }
    }

    public Runnable getTask() throws InterruptedException {
        return taskQueue.take();
    }

    private final class Worker implements Runnable{

        Runnable firstTask;

        Thread thread;

        int finishedTasks;

        public Worker(Runnable firstTask){
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {

        }
    }
}
