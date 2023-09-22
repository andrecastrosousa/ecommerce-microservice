package academy.mindswap.ordersservice.model.status;

import academy.mindswap.ordersservice.exceptions.OrderStatusCannotBePerformedException;

public interface Status {
    void next() throws OrderStatusCannotBePerformedException;
    void previous() throws OrderStatusCannotBePerformedException;
}
