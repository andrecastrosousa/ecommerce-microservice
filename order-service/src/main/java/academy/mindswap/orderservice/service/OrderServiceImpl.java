package academy.mindswap.orderservice.service;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import academy.mindswap.orderservice.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Flux<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public Mono<Order> get(Long id) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND)));
    }

    @Override
    public Mono<Order> update(Long id, Order order) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND)))
                .flatMap(order1 -> {
                    order1.setTotal(order.getTotal());
                    return orderRepository.save(order1);
                });
    }

    @Override
    public Mono<Order> create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return orderRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.ORDER_NOT_FOUND)));
    }
}
