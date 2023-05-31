package fr.epsi.rennes.ws.ordermanager.repository;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
