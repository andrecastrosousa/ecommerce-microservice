package academy.mindswap.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {
    @Id
    private ObjectId id;

    private String name;

    private double price;

    private int quantity;

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

        public ItemBuilder quantity(int quantity) {
            item.setQuantity(quantity);
            return this;
        }

        public Item build() {
            return item;
        }
    }
}
