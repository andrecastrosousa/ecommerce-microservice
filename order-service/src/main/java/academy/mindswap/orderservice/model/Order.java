package academy.mindswap.orderservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Table("orders")
public class Order {

    @Id
    private Long id;

    private double total;

    @Transient
    private List<OrderItem> orderItemList;

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
