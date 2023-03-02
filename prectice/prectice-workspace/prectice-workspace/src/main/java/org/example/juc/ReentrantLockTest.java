package org.example.juc;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();

    // 基本用法
    public void test1(){
        lock.lock();
        try {
            System.out.println("test1");
        } finally {
            lock.unlock();
        }
    }

    // 可重入的特性
    public void test2(){
        lock.lock();
        try {
            System.out.println("test2");
            test1();
        } finally {
            lock.unlock();
        }
    }

    // 可打断的特性
    @SneakyThrows
    public void test3(){
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("t1睡眠500ms, 获取不到锁，进入阻塞队列");
                lock.lockInterruptibly();
                System.out.println("t1获取到锁");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1被打断");
            } finally {
                lock.unlock();
                System.out.println("t1释放锁");
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t2获取锁");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t2被打断");
            } finally {
                lock.unlock();
                System.out.println("t2释放锁");
            }
        }, "t2");

        t1.start();
        t2.start();

        Thread.sleep(1000);
        t1.interrupt();
        System.out.println("主线程尝试打断t1");

        t2.interrupt();
        System.out.println("主线程尝试打断t2");
    }

    // 尝试获取锁
    public void test4(){
        Thread t1 = new Thread(() -> {
            if (!lock.tryLock()){
                System.out.println("t1没有获取到锁");
                return;
            }
            Sleeper.sleep(1);
            lock.unlock();
        }, "t1");

        lock.lock();
        t1.start();
    }

    public static void main(String[] args) {

    }
}
