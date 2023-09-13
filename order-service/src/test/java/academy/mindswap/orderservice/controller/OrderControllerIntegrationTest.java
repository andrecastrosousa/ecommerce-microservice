package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.OrderServiceApplication;
import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.repository.OrderRepository;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={OrderServiceApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderControllerIntegrationTest {
    @Autowired
    OrderRepository orderRepository;
    OrderCreateDto orderCreateDto = new OrderCreateDto();

    @BeforeEach
    public void before() {
    }

    @Nested
    @Tag("validations")
    @DisplayName("Orders invalid crud")
    class OrderCrudValidator {
        @Test
        @DisplayName("Get an order not founded")
        void getOrderNotFound() {
            given()
                    .get("/api/orders/10")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Delete an order not founded")
        void deleteOrderNotFound() {
            given()
                    .delete("/api/orders/10")
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
        void post() {
            given()
                    .header("Content-Type", "application/json")
                    .body(orderCreateDto)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(1))
                    .body("total", is(0.0F));
        }

        @Test
        @DisplayName("Get a list of orders associated to user")
        void getOrders() {
            given()
                    .get("/api/orders")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Delete an orders associated to a user")
        void deleteOrder() {
            given()
                    .delete("/api/orders/1")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);

        }
    }
}
