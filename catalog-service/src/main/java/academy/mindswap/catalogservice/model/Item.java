package academy.mindswap.catalogservice.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
public class Item {
    @Id
    private ObjectId id;

    private String name;

    private double price;

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static class ItemBuilder {

        private final Item item;
        private ItemBuilder() {
            item = new Item();
        }

        public ItemBuilder id(ObjectId id) {
            item.setId(id);
            return this;
        }

        public ItemBuilder name(String name) {
            item.setName(name);
            return this;
        }

        public ItemBuilder price(double price) {
            item.setPrice(price);
            return this;
        }

        public Item build() {
            return item;
        }
    }
}
