package academy.mindswap.orderservice.service;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.repository.OrderRepository;
import academy.mindswap.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Flux<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public Mono<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Mono<Order> update(Long id, Order order) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
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
    public void delete(Long id) {
        orderRepository.findById(id)
                .flatMap(order -> orderRepository.delete(order));

    }
}
