package academy.mindswap.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private ObjectId id;
    private String name;
    private double price;
}
