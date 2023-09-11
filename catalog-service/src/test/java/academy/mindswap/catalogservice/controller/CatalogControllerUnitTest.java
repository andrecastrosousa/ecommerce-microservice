package academy.mindswap.catalogservice.controller;

import academy.mindswap.catalogservice.dto.ItemCreateDto;
import academy.mindswap.catalogservice.dto.ItemUpdateDto;
import academy.mindswap.catalogservice.model.Item;
import academy.mindswap.catalogservice.service.CatalogService;
import academy.mindswap.catalogservice.service.CatalogServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CatalogControllerUnitTest {

    private static CatalogService catalogService;
    private static WebTestClient webTestClient;

    @BeforeAll
    public static void setUp() {
        catalogService = mock(CatalogServiceImpl.class);
        CatalogController catalogController = new CatalogController(catalogService);
        webTestClient = WebTestClient.bindToController(catalogController).build();
    }

    @Nested
    @Tag("listItems")
    @DisplayName("List items")
    class ListItemsValidations {
        @Test
        @DisplayName("List items successfully")
        void shouldListItems() {
            Item item1 = Item.builder()
                    .id(ObjectId.get())
                    .name("item 1")
                    .price(0.0)
                    .build();

            Item item2 = Item.builder()
                    .id(ObjectId.get())
                    .name("item 1")
                    .price(0.0)
                    .build();

            when(catalogService.list()).thenReturn(Flux.just(item1, item2));

            webTestClient.get()
                    .uri("/api/items")
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBodyList(Item.class)
                    .hasSize(2);
        }
    }

    @Nested
    @Tag("createItem")
    @DisplayName("Create item")
    class CreateItemValidations {
        @Test
        @DisplayName("Create item successfully")
        void shouldCreateItem() {
            ItemCreateDto itemCreateDto = ItemCreateDto.builder()
                    .name("item 1")
                    .price(0.0)
                    .build();

            Item item = Item.builder()
                    .name("item 1")
                    .price(0.0)
                    .build();

            when(catalogService.create(itemCreateDto)).thenReturn(Mono.just(item));

            webTestClient.post()
                    .uri("/api/items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(itemCreateDto), ItemCreateDto.class)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBody(Item.class)
                    .isEqualTo(item);
        }
    }

    @Nested
    @Tag("updateItem")
    @DisplayName("Update item")
    class UpdateItemValidations {
        @Test
        @DisplayName("Update item successfully")
        void shouldUpdateItem() {
            final ObjectId objectId = new ObjectId();

            ItemUpdateDto itemUpdateDto = ItemUpdateDto.builder()
                    .name("item 1")
                    .price(0.0)
                    .build();

            Item item = Item.builder()
                    .id(objectId)
                    .name("item 1")
                    .price(0.0)
                    .build();

            when(catalogService.update(objectId, itemUpdateDto)).thenReturn(Mono.just(item));


            webTestClient.put()
                    .uri("/api/items/" + objectId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(itemUpdateDto)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBody(Item.class)
                    .value(incomingItem -> {
                        assertEquals(incomingItem.getName(), item.getName());
                        assertEquals(incomingItem.getPrice(), item.getPrice());
                    });
        }
    }

    @Nested
    @Tag("deleteItem")
    @DisplayName("Delete item")
    class DeleteItemValidations {
        @Test
        @DisplayName("Delete item successfully")
        void shouldDeleteItem() {
            final ObjectId objectId = new ObjectId();

            when(catalogService.delete(objectId)).thenReturn(Mono.empty());


            webTestClient.delete()
                    .uri("/api/items/" + objectId)
                    .exchange()
                    .expectStatus()
                    .isOk();
        }

        @Test
        @DisplayName("Delete a not found item")
        void shouldDeleteItemNotFound() {
            final ObjectId objectId = new ObjectId();

            when(catalogService.delete(objectId))
                    .thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")));

            webTestClient.delete()
                    .uri("/api/orders/" + objectId)
                    .exchange()
                    .expectStatus()
                    .isNotFound();
        }
    }
}
