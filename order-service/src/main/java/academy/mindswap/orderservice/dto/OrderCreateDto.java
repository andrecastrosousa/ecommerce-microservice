package academy.mindswap.orderservice.dto;

import academy.mindswap.orderservice.model.OrderItem;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderCreateDto {

    private double total;

    private List<OrderItem> orderItemList;
}
