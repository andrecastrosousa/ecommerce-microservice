package academy.mindswap.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Item {
    @Id
    private Long id;

    private double price;
}
