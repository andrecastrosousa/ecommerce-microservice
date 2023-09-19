package academy.mindswap.orderservice.controller;

import academy.mindswap.orderservice.dto.OrderCreateDto;
import academy.mindswap.orderservice.dto.OrderUpdateDto;
import academy.mindswap.orderservice.model.Order;
import academy.mindswap.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> list() {
        return orderService.listAll();
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return orderService.get(id);
    }

    @PostMapping
    public Order create(@RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto) {
        return orderService.update(id, orderUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

}
