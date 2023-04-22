package org.example.workspace.日期0421_设计模式;


class 日期0421_Constant {
    // 常量
}
public class 设计模式_状态模式_问题引入 {

    public static void main(String[] args) {
        Lift lift = new Lift();

        lift.setState(ILift.OPENING_STATE);
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
    }



}
interface ILift{
    public static int OPENING_STATE = 1;
    public static int CLOSING_STATE = 2;
    public static int RUNNING_STATE = 3;
    public static int STOPPING_STATE = 4;

    public void setState(int state);

    public void open();
    public void close();
    public void stop();
    public void run();
}

class Lift implements ILift{
    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void open() {
        switch (state){
            case OPENING_STATE:
                // 什么都不用做
                break;
            case CLOSING_STATE:
                System.out.println("打开电梯");
                setState(OPENING_STATE);
                break;
            case STOPPING_STATE:
                System.out.println("打开电梯");
                setState(OPENING_STATE);
                break;
            case RUNNING_STATE:
                // 运行状态不能开门
                break;
        }
    }

    @Override
    public void close() {
        switch (state){
            case OPENING_STATE:
                System.out.println("关闭电梯");
                setState(CLOSING_STATE);
                break;
            case CLOSING_STATE:
                // 本来就是关门的
                break;
            case STOPPING_STATE:
                // 本来也是关门的
                break;
            case RUNNING_STATE:
                // 不用做操作
                break;
        }
    }

    @Override
    public void stop() {
        switch (state){
            case OPENING_STATE:
                // 开门状态下，电梯是停止的，所以不做操作
                break;
            case CLOSING_STATE:
                System.out.println("停止电梯");
                setState(STOPPING_STATE);
                break;
            case STOPPING_STATE:
                // 本来就是停止的
                break;
            case RUNNING_STATE:
                System.out.println("停止电梯");
                setState(STOPPING_STATE);
                break;
        }
    }

    @Override
    public void run() {
        switch (state){
            case OPENING_STATE:
                // 什么都不用做
                break;
            case CLOSING_STATE:
                System.out.println("运行电梯");
                setState(RUNNING_STATE);
                break;
            case STOPPING_STATE:
                System.out.println("运行电梯");
                setState(RUNNING_STATE);
                break;
            case RUNNING_STATE:
                // 本来就是运行的
                break;
        }
    }
}




