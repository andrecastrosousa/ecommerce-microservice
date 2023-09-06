package academy.mindswap.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;

    public OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private final Order order;

        private OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder id(Long id) {
            order.setId(id);
            return this;
        }

        public OrderBuilder total(double total) {
            order.setTotal(total);
            return this;
        }

        public OrderBuilder orderItemList(List<OrderItem> orderItemList) {
            order.setOrderItemList(orderItemList);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
