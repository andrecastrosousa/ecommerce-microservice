package academy.mindswap.catalogservice.repository;

import academy.mindswap.catalogservice.model.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CatalogRepository extends ReactiveMongoRepository<Item, ObjectId> {
}
