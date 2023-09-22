package academy.mindswap.ordersservice.model.status;

import academy.mindswap.ordersservice.model.Order;

public class OrderPending extends OrderState {
    protected OrderPending(Order order) {
        super(order);
    }

    @Override
    public void next() {
        order.setStatus(OrderStatus.PROCESSING);
    }

    @Override
    public void previous() {
        order.setStatus(OrderStatus.CANCELLED);
    }
}
