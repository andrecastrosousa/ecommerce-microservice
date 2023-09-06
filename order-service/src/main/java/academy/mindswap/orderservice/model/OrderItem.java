package academy.mindswap.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    private int quantity;
}
