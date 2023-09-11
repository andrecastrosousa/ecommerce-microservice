package academy.mindswap.orderservice.service;


import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
class OrderServiceUnitTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Test
    void shouldGetOrder() {
        Order order = Order.builder()
                .id(1L)
                .total(0.0)
                .orderItemList(new ArrayList<>())
                .build();

        when(orderRepository.findById(1L)).thenReturn(Mono.just(order));
        Mono<Order> userMono = orderService.get(1L);

        StepVerifier
                .create(userMono)
                .consumeNextWith(newOrder -> {
                    Assertions.assertEquals(newOrder, order);
                })
                .verifyComplete();
    }

    @Test
    void shouldGetOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Mono.empty());
        Mono<Order> userMono = orderService.get(1L);
        StepVerifier
                .create(userMono)
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException)
                .verify();
    }
}
