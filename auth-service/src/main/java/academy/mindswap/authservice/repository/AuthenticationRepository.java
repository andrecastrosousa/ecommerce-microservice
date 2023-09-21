package academy.mindswap.authservice.repository;

import academy.mindswap.authservice.model.Token;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<Token, Long> {
}
