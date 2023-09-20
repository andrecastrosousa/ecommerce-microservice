package academy.mindswap.ordersservice.dto;

import academy.mindswap.ordersservice.model.OrderItem;
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
