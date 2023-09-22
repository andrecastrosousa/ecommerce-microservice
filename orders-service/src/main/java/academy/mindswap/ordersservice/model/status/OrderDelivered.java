package academy.mindswap.ordersservice.model.status;

import academy.mindswap.ordersservice.model.Order;

public class OrderDelivered extends OrderState {
    protected OrderDelivered(Order order) {
        super(order);
    }

    @Override
    public void previous() {
        order.setStatus(OrderStatus.PROCESSING);
    }
}
