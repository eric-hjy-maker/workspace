package org.example.juc;

public class 类0304保护性暂停模式 {

    public static void main(String[] args) {
        保护性暂停模式_sleep();
    }

    public void 保护性暂停模式(){
        Thread t1 = new Thread(() -> {
            while (true){
                Thread current = Thread.currentThread();
                if (current.isInterrupted()){
                    System.out.println("善后处理");
                    break;
                }
                System.out.println("程序运行中");
            }
        }, "t1");
        t1.start();
        Sleeper.sleep(1);
        t1.interrupt();
    }

    public static void 保护性暂停模式_sleep(){
        Thread t1 = new Thread(() -> {
            while (true){
                Thread current = Thread.currentThread();
                if (current.isInterrupted()){
                    System.out.println("善后处理");
                    break;
                }
                try {
                    Thread.sleep(100);
                    System.out.println("程序运行中");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("程序被打断，并抛出了异常");
                    // 因为 sleep方法 会将 interrupted 重新设置为 false, 所以这里要做纠正
                    current.interrupt();
                }
            }
        }, "t1");
        t1.start();
        Sleeper.sleep(1);
        t1.interrupt();
    }
}
