package academy.mindswap.catalogservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class ItemCreateDto {
    private String name;
    private double price;
}
