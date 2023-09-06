package academy.mindswap.orderservice.repository;

import academy.mindswap.orderservice.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
