package academy.mindswap.ordersservice.model.status;

import academy.mindswap.ordersservice.model.Order;

public class OrderProcessing extends OrderState {
    protected OrderProcessing(Order order) {
        super(order);
    }

    @Override
    public void next() {
        order.setStatus(OrderStatus.DELIVERED);
    }

    @Override
    public void previous() {
        order.setStatus(OrderStatus.PENDING);
    }
}
