package academy.mindswap.userservice.repository;

import academy.mindswap.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
