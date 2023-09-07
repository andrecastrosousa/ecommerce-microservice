package academy.mindswap.orderservice.service;

import academy.mindswap.orderservice.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Flux<Order> listAll();

    Mono<Order> get(Long id);

    Mono<Order> update(Long id, Order order);

    Mono<Order> create(Order order);

    Mono<Void> delete(Long id);
}
