package academy.mindswap.ordersservice.service;

import academy.mindswap.ordersservice.exceptions.OrderStatusCannotBePerformedException;
import academy.mindswap.ordersservice.model.Order;
import academy.mindswap.ordersservice.model.status.OrderState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    static final String TOPIC_EXCHANGE_NAME = "exchange";

    static final String QUEUE_NAME = "order-process";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void removeStock(Order order) throws JsonProcessingException, OrderStatusCannotBePerformedException {
        ObjectMapper objectMapper = new ObjectMapper();

        // TODO: it will replace when token will be use
        order.setEmail("test@gmail.com");

        String queuePlayloadString = objectMapper.writeValueAsString(order);

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "foo.bar.baz", queuePlayloadString);
    }
}
