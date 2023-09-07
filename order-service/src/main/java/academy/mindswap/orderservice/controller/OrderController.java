package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Flux<Order> list() {
        return orderService.listAll();
    }

    @GetMapping("/{id}")
    public Mono<Order> get(@PathVariable Long id) {
        return orderService.get(id);
    }

    @PostMapping
    public Mono<Order> create(Order order) {
        return orderService.create(order);
    }

    @PutMapping("/{id}")
    public Mono<Order> update(@PathVariable Long id, Order order) {
        return orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return orderService.delete(id);
    }

}
