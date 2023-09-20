package academy.mindswap.catalogservice.dto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemUpdateDto {
    private String name;
    private double price;
    private int quantity;
}
