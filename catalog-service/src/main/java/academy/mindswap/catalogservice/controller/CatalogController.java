package academy.mindswap.catalogservice.controller;

import academy.mindswap.catalogservice.dto.ItemCreateDto;
import academy.mindswap.catalogservice.dto.ItemUpdateDto;
import academy.mindswap.catalogservice.model.Item;
import academy.mindswap.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/items")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public Flux<Item> list() {
        return catalogService.list();
    }

    @GetMapping("/{id}")
    public Mono<Item> get(@PathVariable ObjectId id) {
        return catalogService.get(id);
    }

    @PostMapping
    public Mono<Item> create(@RequestBody ItemCreateDto itemCreateDto) {
        return catalogService.create(itemCreateDto);
    }

    @PutMapping("/{id}")
    public Mono<Item> update(@PathVariable ObjectId id, @RequestBody ItemUpdateDto itemUpdateDto) {
        return catalogService.update(id, itemUpdateDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable ObjectId id) {
        return catalogService.delete(id);
    }
}
