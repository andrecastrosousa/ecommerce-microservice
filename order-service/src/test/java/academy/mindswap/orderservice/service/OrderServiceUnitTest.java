package academy.mindswap.orderservice.service;


import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.dto.OrderUpdateDto;
import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

            when(orderRepository.findAll()).thenReturn(new ArrayList<>(List.of(order1, order2)));
            List<Order> orders = orderService.listAll();

            assertEquals(2, orders.size());
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

            when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(order));
            Order actualOrder = orderService.get(1L);

            assertEquals(order, actualOrder);

            verify(orderRepository, times(1)).findById(1L);
        }

        @Test
        @DisplayName("Get a not found order")
        void shouldGetOrderNotFound() {
            when(orderRepository.findById(1L)).thenReturn(null);


            ResponseStatusException thrown = assertThrows(
                    ResponseStatusException.class,
                    () -> orderService.get(1L),
                    "Order not found"
            );

            assertEquals("Order not found", thrown.getMessage());

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
            OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                    .orderItemList(new ArrayList<>())
                    .total(0.0)
                    .build();

            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.save(order)).thenReturn(order);
            Order actualOrder = orderService.create(orderCreateDto);

            assertEquals(order, actualOrder);

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
            OrderUpdateDto orderUpdateDto = OrderUpdateDto.builder()
                    .total(0.0)
                    .build();

            Order order = Order.builder()
                    .id(1L)
                    .total(0.0)
                    .orderItemList(new ArrayList<>())
                    .build();

            when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
            when(orderRepository.save(order)).thenReturn(order);
            Order actualOrder = orderService.update(order.getId(), orderUpdateDto);

            assertEquals(order, actualOrder);

            verify(orderRepository, times(1)).findById(order.getId());
            verify(orderRepository, times(1)).save(order);
        }

        @Test
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

            when(orderRepository.findById(order.getId())).thenReturn(null);

            Order actualOrder = orderService.update(order.getId(), orderUpdateDto);

            ResponseStatusException thrown = assertThrows(
                    ResponseStatusException.class,
                    () -> orderService.update(1L, orderUpdateDto),
                    "Order not found"
            );

            assertEquals("Order not found", thrown.getMessage());

            verify(orderRepository, times(1)).findById(order.getId());
            verify(orderRepository, times(0)).save(order);
        }
    }
}
