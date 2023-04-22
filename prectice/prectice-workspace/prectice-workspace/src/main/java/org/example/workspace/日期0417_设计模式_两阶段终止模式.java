package org.example.workspace;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class 日期0417_设计模式_两阶段终止模式 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("线程被停止");
                    break;
                }
                try {
                    log.debug("线程运行中");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    log.debug("线程睡眠中被打断");
                    Thread.currentThread().interrupt();
                }
            }
        }, "t1");

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
        log.debug("2 秒后，打断线程");
    }


}

class Test {
    public static void main(String[] args) {
        // 多线程顺序打印 1 到 100
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i += 2) {
                System.out.println(i);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 2; i <= 100; i += 2) {
                System.out.println(i);
            }
        }, "t2");

    }
}
