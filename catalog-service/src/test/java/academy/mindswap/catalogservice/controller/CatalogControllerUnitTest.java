package academy.mindswap.catalogservice.controller;

import academy.mindswap.catalogservice.model.Item;
import academy.mindswap.catalogservice.service.CatalogService;
import academy.mindswap.catalogservice.service.CatalogServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

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
                    .expectBodyList(Item.class)
                    .hasSize(2);
        }
    }
}
