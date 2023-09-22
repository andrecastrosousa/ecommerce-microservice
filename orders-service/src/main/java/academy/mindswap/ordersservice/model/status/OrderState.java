package academy.mindswap.ordersservice.model.status;

import academy.mindswap.ordersservice.exceptions.OrderStatusCannotBePerformedException;
import academy.mindswap.ordersservice.model.Order;

public abstract class OrderState implements Status {

    protected Order order;

    protected OrderState(Order order) {
        this.order = order;
    }

    public static Status buildState(Order order) {
        return switch (order.getStatus()) {
            case PENDING -> new OrderPending(order);
            case CANCELLED -> new OrderCancelled(order);
            case DELIVERED -> new OrderDelivered(order);
            case PROCESSING -> new OrderProcessing(order);
        };
    }

    @Override
    public void next() throws OrderStatusCannotBePerformedException {
        throw new OrderStatusCannotBePerformedException("Cannot perform next status on " + order.getStatus());
    }

    @Override
    public void previous() throws OrderStatusCannotBePerformedException {
        throw new OrderStatusCannotBePerformedException("Cannot perform previous status on " + order.getStatus());
    }
}
