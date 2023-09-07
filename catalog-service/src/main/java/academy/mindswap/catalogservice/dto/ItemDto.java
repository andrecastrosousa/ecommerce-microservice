package academy.mindswap.catalogservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@Data
@RequiredArgsConstructor
@Builder
public class ItemDto {
    private ObjectId id;
    private String name;
    private double price;
}
