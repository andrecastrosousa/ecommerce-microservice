package academy.mindswap.ordersservice.controller;

import academy.mindswap.ordersservice.dto.OrderUpdateDto;
import academy.mindswap.ordersservice.model.Order;
import academy.mindswap.ordersservice.dto.OrderCreateDto;
import academy.mindswap.ordersservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

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

    @PutMapping("/{id}/process")
    public Order process(@PathVariable Long id) throws JsonProcessingException {
        return orderService.process(id);
    }
}
