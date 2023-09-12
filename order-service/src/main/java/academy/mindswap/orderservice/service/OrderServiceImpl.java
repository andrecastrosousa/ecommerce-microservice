package academy.mindswap.orderservice.service;

import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.dto.OrderUpdateDto;
import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import academy.mindswap.orderservice.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
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
    public Order update(Long id, OrderUpdateDto order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND);
        }

        Order order =
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND)))
                .flatMap(order1 -> {
                    order1.setTotal(order.getTotal());
                    return orderRepository.save(order1);
                });
    }

    @Override
    public Order create(OrderCreateDto order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
