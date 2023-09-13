package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.dto.OrderUpdateDto;
import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
public class OrderControllerUnitTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Nested
    @Tag("list")
    @DisplayName("List orders unit test")
    class ListOrdersValidations {
        @Test
        @SneakyThrows
        @DisplayName("List orders successfully")
        void shouldListOrders() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.listAll()).thenReturn(new ArrayList<>(List.of(order, new Order())));

            mockMvc.perform(
                        get("/api/orders")
                                .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @Tag("get")
    @DisplayName("Get order unit test")
    class GetOrderValidations {
        @Test
        @SneakyThrows
        @DisplayName("Get order successfully")
        void shouldGetOrder() {
            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.get(1L)).thenReturn(order);

            mockMvc.perform(
                        get("/api/orders/{id}", 1)
                                .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @SneakyThrows
        @DisplayName("Get order successfully")
        void shouldGetOrderNotFound() {
            when(orderService.get(1L))
                    .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

            mockMvc.perform(
                            get("/api/orders/{id}", 1)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @Tag("create")
    @DisplayName("Create order unit test")
    class CreateOrderValidations {
        @SneakyThrows
        @Test
        @DisplayName("Create order successfully")
        void shouldCreateOrder() {
            OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                    .orderItemList(new ArrayList<>())
                    .total(0.0)
                    .build();

            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.create(orderCreateDto)).thenReturn(order);

            mockMvc.perform(
                        post("/api/orders")
                            .content(mapper.writeValueAsBytes(orderCreateDto))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        }
    }

    @Nested
    @Tag("update")
    @DisplayName("Update order unit test")
    class UpdateOrderValidations {
        @Test
        @SneakyThrows
        @DisplayName("Update order successfully")
        void shouldUpdateOrder() {
            OrderUpdateDto orderUpdateDto = OrderUpdateDto.builder()
                    .total(0.0)
                    .build();

            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.update(order.getId(), orderUpdateDto)).thenReturn(order);

            mockMvc.perform(
                    put("/api/orders/{id}", 1)
                            .content(mapper.writeValueAsBytes(orderUpdateDto))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        @SneakyThrows
        @DisplayName("Update a not found order")
        void shouldUpdateOrderNotFound() {
            OrderUpdateDto orderUpdateDto = OrderUpdateDto.builder()
                    .total(0.0)
                    .build();

            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderService.update(order.getId(), orderUpdateDto))
                    .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

            mockMvc.perform(
                            put("/api/orders/{id}", 1)
                                    .content(mapper.writeValueAsBytes(orderUpdateDto))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @Tag("deleteOrder")
    @DisplayName("Delete order unit test")
    class DeleteOrderValidations {
        @Test
        @SneakyThrows
        @DisplayName("Delete an order successfully")
        void shouldDeleteAnOrder() {
            mockMvc.perform(
                    delete("/api/orders/{id}", 1)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        @SneakyThrows
        @DisplayName("Delete a not found order")
        void shouldDeleteANotFoundOrder() {
            mockMvc.perform(
                            delete("/api/orders/{id}", 1)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }
}
