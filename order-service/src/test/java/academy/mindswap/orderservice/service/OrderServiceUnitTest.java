package academy.mindswap.orderservice.service;


import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OrderServiceUnitTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Nested
    @Tag("listOrders")
    @DisplayName("List orders")
    class ListOrdersValidations {
        @Test
        @DisplayName("List orders successfully")
        void shouldListOrders() {
            Order order1 = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            Order order2 = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.findAll()).thenReturn(Flux.just(order1, order2));
            Flux<Order> orderFlux = orderService.listAll();

            StepVerifier
                    .create(orderFlux)
                    .expectNextCount(2L)
                    .expectComplete()
                    .verify();

            verify(orderRepository, times(1)).findAll();
        }
    }

    @Nested
    @Tag("getOrder")
    @DisplayName("Get order")
    class GetOrderValidations {
        @Test
        @DisplayName("Get order successfully")
        void shouldGetOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.findById(1L)).thenReturn(Mono.just(order));
            Mono<Order> orderMono = orderService.get(1L);

            StepVerifier
                    .create(orderMono)
                    .consumeNextWith(newOrder -> {
                        Assertions.assertEquals(newOrder, order);
                    })
                    .verifyComplete();

            verify(orderRepository, times(1)).findById(1L);
        }

        @Test
        @DisplayName("Get a not found order")
        void shouldGetOrderNotFound() {
            when(orderRepository.findById(1L)).thenReturn(Mono.empty());
            Mono<Order> userMono = orderService.get(1L);
            StepVerifier
                    .create(userMono)
                    .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException)
                    .verify();

            verify(orderRepository, times(1)).findById(1L);
        }
    }

    @Nested
    @Tag("createOrder")
    @DisplayName("Create order")
    class CreateOrderValidations {
        @Test
        @DisplayName("Create order successfully")
        void shouldCreateOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.save(order)).thenReturn(Mono.just(order));
            Mono<Order> orderMono = orderService.create(order);

            StepVerifier
                    .create(orderMono)
                    .consumeNextWith(newOrder ->
                            Assertions.assertEquals(newOrder, order))
                    .verifyComplete();

            verify(orderRepository, times(1)).save(order);
        }
    }

    @Nested
    @Tag("updateOrder")
    @DisplayName("Update order")
    class UpdateOrderValidations {
        @Test
        @DisplayName("Update order successfully")
        void shouldUpdateOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.findById(order.getId())).thenReturn(Mono.just(order));
            when(orderRepository.save(order)).thenReturn(Mono.just(order));
            Mono<Order> orderMono = orderService.update(order.getId(), order);

            StepVerifier
                    .create(orderMono)
                    .consumeNextWith(newOrder ->
                            Assertions.assertEquals(newOrder, order))
                    .verifyComplete();

            verify(orderRepository, times(1)).findById(order.getId());
            verify(orderRepository, times(1)).save(order);
        }

        @Test
        @DisplayName("Update a not found order")
        void shouldUpdateOrderNotFound() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.findById(order.getId())).thenReturn(Mono.empty());

            Mono<Order> orderMono = orderService.update(order.getId(), order);

            StepVerifier
                    .create(orderMono)
                    .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException)
                    .verify();

            verify(orderRepository, times(1)).findById(order.getId());
            verify(orderRepository, times(0)).save(order);
        }
    }
}
