package academy.mindswap.orderservice.dto;

import academy.mindswap.orderservice.model.OrderItem;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDto {

    private double total;

    private List<OrderItem> orderItemList;
}
