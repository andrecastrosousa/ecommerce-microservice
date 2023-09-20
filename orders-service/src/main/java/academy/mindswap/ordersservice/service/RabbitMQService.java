package academy.mindswap.ordersservice.service;

import academy.mindswap.ordersservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RabbitMQService {
    void removeStock(Order order) throws JsonProcessingException;
}
