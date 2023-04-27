package org.example.workspace.日期0421_设计模式;

import static org.example.workspace.日期0421_设计模式.StateConstant.*;

class StateConstant {
    public final static OpeningState OPENING_STATE = new OpeningState();
    public final static ClosingState CLOSING_STATE = new ClosingState();
    public final static RunningState RUNNING_STATE = new RunningState();
    public final static StoppingState STOPPING_STATE = new StoppingState();
}

class Context{
    private LiftState liftState;

    public void setLiftState(LiftState liftState){
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open(){
        this.liftState.open();
    }

    public void close(){
        this.liftState.close();
    }

    public void run(){
        this.liftState.run();
    }

    public void stop(){
        this.liftState.stop();
    }
}
abstract class LiftState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();
}

class OpeningState extends LiftState {

    @Override
    public void open() {

    }

    @Override
    public void close() {
        System.out.println("关闭电梯");
        this.context.setLiftState(CLOSING_STATE);
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
class ClosingState extends LiftState {

    @Override
    public void open() {
        System.out.println("关闭电梯");
        this.context.setLiftState(OPENING_STATE);
    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println("运行电梯");
        this.context.setLiftState(RUNNING_STATE);
    }

    @Override
    public void stop() {

    }
}
class RunningState extends LiftState {

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {
        System.out.println("停止电梯");
        this.context.setLiftState(STOPPING_STATE);
    }
}
class StoppingState extends LiftState {

    @Override
    public void open() {

    }

    @Override
    public void close() {
        System.out.println("关闭电梯");
        this.context.setLiftState(CLOSING_STATE);
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}

class 日期0421_设计模式_状态模式 {

    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(OPENING_STATE);
        context.open();
        context.run();
        context.close();
        context.run();
        context.stop();
    }
}