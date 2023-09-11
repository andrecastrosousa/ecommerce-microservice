package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderService;
import academy.mindswap.orderservice.service.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderControllerUnitTest {

    private static OrderService orderService;
    private static WebTestClient webTestClient;

    @BeforeAll
    public static void setUp() {
        orderService = mock(OrderServiceImpl.class);
        OrderController orderController = new OrderController(orderService);
        webTestClient = WebTestClient.bindToController(orderController).build();
    }

    @Nested
    @Tag("list")
    @DisplayName("List orders unit test")
    class ListOrdersValidations {
        @Test
        @DisplayName("List orders successfully")
        void shouldListOrders() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.listAll()).thenReturn(Flux.just(order, new Order()));

            webTestClient.get()
                    .uri("/api/orders")
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBodyList(Order.class)
                    .hasSize(2);
        }
    }

    @Nested
    @Tag("get")
    @DisplayName("Get order unit test")
    class GetOrderValidations {
        @Test
        @DisplayName("Get order successfully")
        void shouldGetOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.get(1L)).thenReturn(Mono.just(order));

            webTestClient.get()
                    .uri("/api/orders/1")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(Order.class)
                    .isEqualTo(order);
        }

        @Test
        @DisplayName("Get order successfully")
        void shouldGetOrderNotFound() {
            when(orderService.get(1L))
                    .thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")));

            webTestClient.get()
                    .uri("/api/orders/1")
                    .exchange()
                    .expectStatus()
                    .isNotFound();
        }
    }

    @Nested
    @Tag("create")
    @DisplayName("Create order unit test")
    class CreateOrderValidations {
        @Test
        @DisplayName("Create order successfully")
        void shouldCreateOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.create(order)).thenReturn(Mono.just(order));

            webTestClient.post()
                    .uri("/api/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(order), Order.class)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBody(Order.class)
                    .isEqualTo(order);
        }
    }

    @Nested
    @Tag("update")
    @DisplayName("Update order unit test")
    class UpdateOrderValidations {
        @Test
        @DisplayName("Update order successfully")
        void shouldUpdateOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.update(order.getId(), order)).thenReturn(Mono.just(order));

            webTestClient.put()
                    .uri("/api/orders/1")
                    .body(Mono.just(order), Order.class)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(Order.class)
                    .isEqualTo(order);
        }

        @Test
        @DisplayName("Update order successfully")
        void shouldUpdateOrderNotFound() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.update(order.getId(), order))
                    .thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")));

            webTestClient.put()
                    .uri("/api/orders/1")
                    .body(Mono.just(order), Order.class)
                    .exchange()
                    .expectStatus()
                    .isNotFound();
        }
    }

    @Nested
    @Tag("deleteOrder")
    @DisplayName("Delete order unit test")
    class DeleteOrderValidations {
        @Test
        @DisplayName("Delete an order successfully")
        void shouldDeleteAnOrder() {
            when(orderService.delete(1L)).thenReturn(Mono.empty());

            webTestClient.delete()
                    .uri("/api/orders/1")
                    .exchange()
                    .expectStatus()
                    .isOk();
        }

        @Test
        @DisplayName("Delete a not found order")
        void shouldDeleteANotFoundOrder() {
            when(orderService.delete(1L))
                    .thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")));


            webTestClient.delete()
                    .uri("/api/orders/1")
                    .exchange()
                    .expectStatus()
                    .isNotFound();
        }
    }
}
