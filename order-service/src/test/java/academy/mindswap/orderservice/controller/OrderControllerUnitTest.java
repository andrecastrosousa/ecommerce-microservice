package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderService;
import academy.mindswap.orderservice.service.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderControllerUnitTest {

    OrderService orderService;
    WebTestClient webTestClient;
    OrderController categoryController;

    Order order = new Order();

    @Before
    public void setUp() {
        orderService = mock(OrderServiceImpl.class);
        categoryController = new OrderController(orderService);
        webTestClient = WebTestClient.bindToController(categoryController).build();

        order.setId(1L);


    }

    @Test
    public void getListOfAllOrders() {
        Order order = Order.builder()
                .id(1L)
                .total(0.0)
                .orderItemList(new ArrayList<>())
                .build();

        when(orderService.listAll()).thenReturn(Flux.just(order, new Order()));

        webTestClient.get()
                .uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .hasSize(2);
    }

    @Test
    public void getAnOrderById() {
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
    public void createAnOrder() {
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
                .expectStatus().isOk()
                .expectBody(Order.class)
                .isEqualTo(order);
    }

    @Test
    public void updateAnOrder() {
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
    public void deleteAnOrder() {
        // when(orderService.delete(1L)).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")));
        when(orderService.delete(1L)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/orders/1")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
