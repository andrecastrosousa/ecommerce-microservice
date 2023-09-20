package academy.mindswap.ordersservice.service;


import academy.mindswap.ordersservice.dto.OrderUpdateDto;
import academy.mindswap.ordersservice.model.Order;
import academy.mindswap.ordersservice.dto.OrderCreateDto;

import java.util.List;

public interface OrderService {
    List<Order> listAll();

    Order get(Long id);

    Order update(Long id, OrderUpdateDto order);

    Order create(OrderCreateDto order);

    void delete(Long id);
}
