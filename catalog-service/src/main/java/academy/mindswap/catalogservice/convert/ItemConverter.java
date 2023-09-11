package academy.mindswap.catalogservice.convert;

import academy.mindswap.catalogservice.dto.ItemCreateDto;
import academy.mindswap.catalogservice.dto.ItemDto;
import academy.mindswap.catalogservice.dto.ItemUpdateDto;
import academy.mindswap.catalogservice.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {
    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }

    public Item fromDto(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .build();
    }

    public Item toEntityFromCreateDto(ItemCreateDto itemCreateDto) {
        return Item.builder()
                .name(itemCreateDto.getName())
                .price(itemCreateDto.getPrice())
                .build();
    }

    public Item toEntityFromUpdateDto(ItemUpdateDto itemUpdateDto) {
        return Item.builder()
                .name(itemUpdateDto.getName())
                .price(itemUpdateDto.getPrice())
                .build();
    }
}
