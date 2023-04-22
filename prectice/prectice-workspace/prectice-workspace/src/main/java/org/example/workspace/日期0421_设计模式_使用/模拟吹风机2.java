package org.example.workspace.日期0421_设计模式_使用;

public class 模拟吹风机2 {
}

class DryerSwitchContext {
    public static final Closing CLOSING = new Closing();
    public static final FirstLevel FIRST_LEVEL = new FirstLevel();
    public static final SecondLevel SECOND_LEVEL = new SecondLevel();
    public static final ThirdLevel THIRD_LEVEL = new ThirdLevel();

    private DryerSwitch dryerSwitch;

    public void setDryerSwitch(DryerSwitch dryerSwitch) {
        this.dryerSwitch = dryerSwitch;
        this.dryerSwitch.setContext(this);
    }

    public void close(){
        this.dryerSwitch.close();
    }

    public void toFirst(){
        this.dryerSwitch.toFirst();
    }

    public void toSecond(){
        this.dryerSwitch.toSecond();
    }

    public void toThird(){
        this.dryerSwitch.toThird();
    }
}
/**
 * 吹风机开关
 */
abstract class DryerSwitch {
    protected DryerSwitchContext context;

    public void setContext(DryerSwitchContext context) {
        this.context = context;
    }
    /**
     * 关闭
     */
    abstract void close();
    /**
     * 调到一级风
     */
    abstract void toFirst();
    /**
     * 调到二级风
     */
    abstract void toSecond();
    /**
     * 调到三级风
     */
    abstract void toThird();
}

class Closing extends DryerSwitch {

    @Override
    void close() {

    }

    @Override
    void toFirst() {
        System.out.println("调到一级风");
        this.context.setDryerSwitch(DryerSwitchContext.FIRST_LEVEL);
    }

    @Override
    void toSecond() {

    }

    @Override
    void toThird() {

    }
}

class FirstLevel extends DryerSwitch {

    @Override
    void close() {
        System.out.println("关闭吹风机");
        this.context.setDryerSwitch(DryerSwitchContext.CLOSING);
    }

    @Override
    void toFirst() {

    }

    @Override
    void toSecond() {
        System.out.println("调到二级风");
        this.context.setDryerSwitch(DryerSwitchContext.SECOND_LEVEL);
    }

    @Override
    void toThird() {

    }
}

class SecondLevel extends DryerSwitch {

    @Override
    void close() {

    }

    @Override
    void toFirst() {
        System.out.println("调到一级风");
        this.context.setDryerSwitch(DryerSwitchContext.FIRST_LEVEL);
    }

    @Override
    void toSecond() {

    }

    @Override
    void toThird() {
        System.out.println("调到三级风");
        this.context.setDryerSwitch(DryerSwitchContext.THIRD_LEVEL);
    }
}

class ThirdLevel extends DryerSwitch {

    @Override
    void close() {

    }

    @Override
    void toFirst() {

    }

    @Override
    void toSecond() {
        System.out.println("调到二级风");
        this.context.setDryerSwitch(DryerSwitchContext.SECOND_LEVEL);
    }

    @Override
    void toThird() {

    }
}