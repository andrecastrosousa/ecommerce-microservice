package academy.mindswap.orderservice.service;

import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.dto.OrderUpdateDto;
import academy.mindswap.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> listAll();

    Order get(Long id);

    Order update(Long id, OrderUpdateDto order);

    Order create(OrderCreateDto order);

    void delete(Long id);
}
