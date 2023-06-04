package fr.epsi.rennes.ws.ordermanager.repository;

import fr.epsi.rennes.ws.ordermanager.generated.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @NonNull
    @Query("select o from orders o where o.id = ?1")
    @Async
    Future<Optional<Order>> getByUUID(@NonNull String id);
}
