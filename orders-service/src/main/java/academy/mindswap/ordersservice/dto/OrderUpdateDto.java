package academy.mindswap.ordersservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderUpdateDto {
    private double total;
}
