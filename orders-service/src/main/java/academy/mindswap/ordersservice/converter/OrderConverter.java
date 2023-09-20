package academy.mindswap.ordersservice.converter;

import academy.mindswap.ordersservice.dto.OrderCreateDto;
import academy.mindswap.ordersservice.dto.OrderUpdateDto;
import academy.mindswap.ordersservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    private OrderConverter() {}

    public Order toEntityFromCreateDto(OrderCreateDto orderCreateDto) {
        return Order.builder()
                .orderItemList(orderCreateDto.getOrderItemList())
                .total(orderCreateDto.getTotal())
                .build();
    }

    public Order toEntityFromUpdateDto(OrderUpdateDto orderUpdateDto) {
        return Order.builder()
                .total(orderUpdateDto.getTotal())
                .build();
    }
}
