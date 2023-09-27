package academy.mindswap.authservice.repository;

import academy.mindswap.authservice.model.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationRepository extends CrudRepository<Token, String> {
    Optional<Token> findByToken(String token);
}
