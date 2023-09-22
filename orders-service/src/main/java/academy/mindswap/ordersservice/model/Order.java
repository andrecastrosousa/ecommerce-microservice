package academy.mindswap.ordersservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;

    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(total, order.total) == 0
                && Objects.equals(id, order.id)
                && Objects.equals(orderItemList, order.orderItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, orderItemList);
    }

    public static OrderBuilder builder() {
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
