package academy.mindswap.orderservice.dto;

import academy.mindswap.orderservice.model.OrderItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDto {

    private double total;

    private List<OrderItem> orderItemList;
}
