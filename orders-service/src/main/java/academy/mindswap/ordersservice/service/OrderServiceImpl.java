package academy.mindswap.ordersservice.service;

import academy.mindswap.ordersservice.converter.OrderConverter;
import academy.mindswap.ordersservice.dto.OrderCreateDto;
import academy.mindswap.ordersservice.dto.OrderUpdateDto;
import academy.mindswap.ordersservice.model.Order;
import academy.mindswap.ordersservice.repository.OrderRepository;
import academy.mindswap.ordersservice.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public List<Order> listAll() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public Order get(Long id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND);
        }

        return existingOrder.get();
    }

    @Override
    public Order update(Long id, OrderUpdateDto orderUpdateDto) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND);
        }

        Order order = existingOrder.get();
        order.setTotal(orderUpdateDto.getTotal());
        orderRepository.save(order);

        return order;
    }

    @Override
    public Order create(OrderCreateDto orderCreateDto) {
        Order order = orderConverter.toEntityFromCreateDto(orderCreateDto);
        orderRepository.save(order);
        return order;
    }

    @Override
    public void delete(Long id) {
        if(!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND);
        }

        orderRepository.deleteById(id);
    }
}
