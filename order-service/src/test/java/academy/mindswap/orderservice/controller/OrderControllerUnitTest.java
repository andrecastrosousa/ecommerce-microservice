package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerUnitTest {

    OrderServiceImpl orderService;
    WebTestClient webTestClient;
    OrderController categoryController;


    @Before
    public void setUp() {
        orderService = mock(OrderServiceImpl.class);
        categoryController = new OrderController(orderService);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getListOfOrdersAssociatedToAnItem() throws Exception {
        when(orderService.listAll()).thenReturn(Flux.just(new Order(), new Order()));
        webTestClient.get()
                .uri("/api/orders")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .hasSize(2);
    }
}
