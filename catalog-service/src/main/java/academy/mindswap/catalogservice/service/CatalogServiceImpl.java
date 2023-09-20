package academy.mindswap.catalogservice.service;

import academy.mindswap.catalogservice.convert.ItemConverter;
import academy.mindswap.catalogservice.dto.ItemCreateDto;
import academy.mindswap.catalogservice.dto.ItemUpdateDto;
import academy.mindswap.catalogservice.model.Item;
import academy.mindswap.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    private final ItemConverter itemConverter;


    @Override
    public Flux<Item> list() {
        return catalogRepository.findAll();
    }

    @Override
    public Mono<Item> get(ObjectId id) {
        return catalogRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found")));
    }

    @Override
    public Mono<Item> create(ItemCreateDto itemCreateDto) {
        return catalogRepository.save(itemConverter.toEntityFromCreateDto(itemCreateDto));
    }

    @Override
    public Mono<Item> update(ObjectId id, ItemUpdateDto itemUpdateDto) {
        return catalogRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found")))
                .flatMap(foundItem -> {
                    Item item = itemConverter.toEntityFromUpdateDto(itemUpdateDto);
                    foundItem.setPrice(item.getPrice());
                    foundItem.setName(item.getName());
                    foundItem.setQuantity(item.getQuantity());
                    return catalogRepository.save(foundItem);
                }
        );
    }

    @Override
    public Mono<Void> delete(ObjectId id) {
        return  catalogRepository.deleteById(id);
    }
}
