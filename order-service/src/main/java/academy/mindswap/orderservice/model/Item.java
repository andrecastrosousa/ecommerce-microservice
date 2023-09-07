package academy.mindswap.orderservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("item")
public class Item {
    @Id
    private Long id;

    private double price;
}
