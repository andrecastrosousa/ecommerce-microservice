package academy.mindswap.ordersservice.repository;

import academy.mindswap.ordersservice.model.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Native;

@Profile("test")
public interface OrderTestRepository extends JpaRepository<Order, Long> {
    @Query("ALTER TABLE Items AUTO_INCREMENT = 1")
    @Modifying
    @Transactional
    public void clearDB();
}
