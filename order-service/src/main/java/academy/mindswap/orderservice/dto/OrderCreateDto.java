package academy.mindswap.orderservice.dto;

import academy.mindswap.orderservice.model.OrderItem;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
public class OrderCreateDto {

    private double total;

    private List<OrderItem> orderItemList;
}
