package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.repository.ItemRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Throwable create(Item item) {
        log.info("Creating item: {}", item.toString());
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            throw new ValidationException("Error while creating item: %s".formatted(e.getMessage()));
        }
        return null;
    }

    public Item getById(int itemId) throws ValidationException {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            return item;
        }
        throw new ValidationException("Item not found");
    }

    /**
     * Rends l'article invendable en mettant sa quantité à 0 et en le marquant comme supprimé
     * Garde l'article en base de données pour l'historique des commandes / prix
     *
     * @param item l'article à 'supprimer'
     */
    public void delete(Item item) {
        log.info("'Deleting' item: {}", item);
        Item dbItem = getById(item.getId());
        dbItem.setArchived(true);
        dbItem.setQuantity(0);
        itemRepository.save(dbItem);
    }

    public Item update(Item item) {
        Item dbItem = getById(item.getId());
        dbItem.setName(item.getName());
        dbItem.setUnitPrice(item.getUnitPrice());
        dbItem.setQuantity(item.getQuantity());
        return itemRepository.save(dbItem);
    }

    /**
     * Ajoute la quantité spécifiée d'un article au stock
     *
     * @param orderItem l'article dont on doit incrémenter le stock
     */
    public void addQuantity(Item orderItem) {
        log.info("Adding {} QTY to {} ", orderItem.getQuantity(), orderItem.getName());
        Item dbItem = getById(orderItem.getId());
        dbItem.setQuantity(dbItem.getQuantity() + orderItem.getQuantity());
        itemRepository.save(dbItem);
    }

    public void subOne(Item orderItem) throws ValidationException {
        Item dbItem = getById(orderItem.getId());
        if (dbItem.getQuantity() - 1 < 0) {
            throw new ValidationException("Pas assez de stock sur l'objet " + dbItem.getName());
        }
        log.info("Subtracting 1 QTY to {} ", dbItem.getName());
        dbItem.setQuantity(dbItem.getQuantity() - 1);
        update(dbItem);
    }

    @Transactional
    public void addAll(List<Item> items) {
        List<Throwable> errors = new LinkedList<>();
        for (Item item : items) {
            errors.add(create(item));
        }
        if (errors.stream().anyMatch(Objects::nonNull)) {
            log.error("Error while creating items: {}", errors.stream().filter(Objects::nonNull).toList());
        }

    }

    public Iterable<Item> getAll() {
        return itemRepository.findAll().stream().filter(item -> !item.isArchived()).toList();
    }
}
