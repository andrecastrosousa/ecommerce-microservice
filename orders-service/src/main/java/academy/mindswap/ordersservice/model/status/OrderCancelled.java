package academy.mindswap.ordersservice.model.status;

import academy.mindswap.ordersservice.model.Order;

public class OrderCancelled extends OrderState {
    protected OrderCancelled(Order order) {
        super(order);
    }

    @Override
    public void next() {
        order.setStatus(OrderStatus.PENDING);
    }
}
