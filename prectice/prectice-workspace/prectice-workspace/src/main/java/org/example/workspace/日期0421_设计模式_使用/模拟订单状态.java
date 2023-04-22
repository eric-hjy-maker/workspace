package org.example.workspace.日期0421_设计模式_使用;

public class 模拟订单状态 {
    public static void main(String[] args) {
        OrderStateContext context = new OrderStateContext();
        context.setState(OrderStateContext.NO_ORDER);
        context.takeOrder();
        context.pay();
        context.postGoods();
        context.deliverGoods();
        context.confirm();
        context.applyRefund();
    }
}

class OrderStateContext {
    public static final NoOrder NO_ORDER = new NoOrder();
    public static final WaitingPay WAITING_PAY = new WaitingPay();
    public static final Canceled CANCELED = new Canceled();
    public static final WaitingPost WAITING_POST = new WaitingPost();
    public static final Posted POSTED = new Posted();
    public static final Signed SIGNED = new Signed();
    public static final Confirmed CONFIRMED = new Confirmed();
    public static final RuFunded RU_FUNDED = new RuFunded();

    private OrderState state;

    public void setState(OrderState state) {
        this.state = state;
        this.state.setContext(this);
    }

    public void takeOrder() {
        this.state.takeOrder();
    }

    public void cancelOrder() {
        this.state.cancelOrder();
    }

    public void pay() {
        this.state.pay();
    }

    public void postGoods() {
        this.state.postGoods();
    }

    public void deliverGoods() {
        this.state.deliverGoods();
    }

    public void confirm() {
        this.state.confirm();
    }

    public void applyRefund() {
        this.state.applyRefund();
    }
}


abstract class OrderState {

    protected OrderStateContext context;

    public void setContext(OrderStateContext context) {
        this.context = context;
    }

    /**
     * 下单
     */
    public abstract void takeOrder();

    /**
     * 取消订单
     */
    public abstract void cancelOrder();

    /**
     * 付款
     */
    public abstract void pay();

    /**
     * 发货
     */
    public abstract void postGoods();

    /**
     * 送货
     */
    public abstract void deliverGoods();

    /**
     * 确认收货
     */
    public abstract void confirm();

    /**
     * 申请退款
     */
    public abstract void applyRefund();
}

class NoOrder extends OrderState {

    @Override
    public void takeOrder() {
        System.out.println("下单");
        this.context.setState(OrderStateContext.WAITING_PAY);
    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {

    }
}

class WaitingPay extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {
        System.out.println("取消订单");
        this.context.setState(OrderStateContext.CANCELED);
    }

    @Override
    public void pay() {
        System.out.println("付款");
        this.context.setState(OrderStateContext.WAITING_POST);
    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {

    }
}

class Canceled extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {

    }
}

class WaitingPost extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {
        System.out.println("发货");
        this.context.setState(OrderStateContext.POSTED);
    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {
        System.out.println("申请退款");
        this.context.setState(OrderStateContext.RU_FUNDED);
    }
}

class Posted extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {
        System.out.println("送货");
        this.context.setState(OrderStateContext.SIGNED);
    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {
        System.out.println("申请退款");
        this.context.setState(OrderStateContext.RU_FUNDED);
    }
}

class Signed extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {
        System.out.println("确认收货");
        this.context.setState(OrderStateContext.CONFIRMED);
    }

    @Override
    public void applyRefund() {
        System.out.println("申请退款");
        this.context.setState(OrderStateContext.RU_FUNDED);
    }
}

class Confirmed extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {
        System.out.println("申请退款");
        this.context.setState(OrderStateContext.RU_FUNDED);
    }
}

class RuFunded extends OrderState {

    @Override
    public void takeOrder() {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void pay() {

    }

    @Override
    public void postGoods() {

    }

    @Override
    public void deliverGoods() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void applyRefund() {

    }
}
