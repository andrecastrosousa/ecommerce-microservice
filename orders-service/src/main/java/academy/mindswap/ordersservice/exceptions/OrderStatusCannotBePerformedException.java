package academy.mindswap.ordersservice.exceptions;

public class OrderStatusCannotBePerformedException extends Exception {
    public OrderStatusCannotBePerformedException(String message) {
        super(message);
    }
}
