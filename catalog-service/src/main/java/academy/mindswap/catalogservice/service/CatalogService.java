package academy.mindswap.catalogservice.service;

import academy.mindswap.catalogservice.dto.ItemCreateDto;
import academy.mindswap.catalogservice.dto.ItemUpdateDto;
import academy.mindswap.catalogservice.model.Item;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogService {
    Flux<Item> list();

    Mono<Item> get(ObjectId id);

    Mono<Item> create(ItemCreateDto itemCreateDto);

    Mono<Item> update(ObjectId id, ItemUpdateDto itemUpdateDto);

    Mono<Void> delete(ObjectId id);
}
