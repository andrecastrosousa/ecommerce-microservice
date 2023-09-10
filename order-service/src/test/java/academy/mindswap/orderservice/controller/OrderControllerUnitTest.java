package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderService;
import academy.mindswap.orderservice.service.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        when(orderService.listAll()).thenReturn(Flux.just(order, new Order()));
        when(orderService.get(1L)).thenReturn(Mono.just(order));
        when(orderService.create(order)).thenReturn(Mono.just(order));
        when(orderService.update(order.getId(), order)).thenReturn(Mono.just(order));
    }

    @Test
    public void getListOfAllOrders() {
        webTestClient.get()
                .uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .hasSize(2);
    }

    @Test
    public void getAnOrderById() {
        webTestClient.get()
                .uri("/api/orders/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .isEqualTo(order);
    }

    @Test
    public void createAnOrder() {
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
        webTestClient.delete()
                .uri("/api/orders/1")
                .exchange()
                .expectStatus().isOk();
    }
}
