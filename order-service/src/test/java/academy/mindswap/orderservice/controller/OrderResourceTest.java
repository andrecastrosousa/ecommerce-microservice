package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.OrderServiceApplication;
import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={OrderServiceApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@RequiredArgsConstructor
public class OrderResourceTest {
    // OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    // ItemRepository itemRepository;
    // OrderConverter orderConverter;
    OrderCreateDto orderCreateDto = new OrderCreateDto();

    @BeforeEach
    @Transactional
    public void before() {
        /*orderItemRepository.deleteAll();
        itemRepository.deleteAll();
        orderRepository.deleteAll();

        orderItemRepository.reset();
        itemRepository.reset();
        orderRepository.reset();

        orderCreateDto.setOrderDatetime(LocalDateTime.now());
        Order order = orderConverter.toEntityFromCreateDto(orderCreateDto);

        orderRepository.save(order);*/
    }

    @Nested
    @Tag("validations")
    @DisplayName("Orders invalid crud")
    class OrderCrudValidator {
        @Test
        @DisplayName("Get an order not founded")
        public void getOrderNotFound() {
            given()
                    .get("/api/orders/2")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Delete an order not founded")
        public void deleteOrderNotFound() {
            given()
                    .delete("/api/orders/2")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }
    }

    @Nested
    @Tag("crud")
    @DisplayName("Orders valid crud")
    class ItemCrudTests {
        @Test
        @DisplayName("Create an order and associate to a user")
        public void post() {
            given()
                    .header("Content-Type", "application/json")
                    .body(orderCreateDto)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(2))
                    .body("total", is(0.0F));
        }

        @Test
        @DisplayName("Get a list of orders associated to user")
        public void getOrders() {
            given()
                    .get("/api/orders")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Delete an orders associated to a user")
        public void deleteOrder() {
            given()
                    .delete("/api/orders/1")
                    .then()
                    .statusCode(HttpStatus.SC_OK);

        }
    }
}
