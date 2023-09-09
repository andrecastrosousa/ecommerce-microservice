package academy.mindswap.orderservice.service;


import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {

    @InjectMocks
    OrderServiceImpl orderItemService;

    @Mock
    OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        Order order = new Order();

        when(orderRepository.findById(1L)).thenReturn(Mono.just(order));
    }

    @Test
    void getListOfOrdersAssociatedToAnItem() {
        assertEquals( new Order(), orderItemService.get(1L));
    }
}
