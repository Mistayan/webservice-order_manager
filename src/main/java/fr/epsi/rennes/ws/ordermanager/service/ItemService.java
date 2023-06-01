package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.repository.ItemRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void createItem(Item item) {
        itemRepository.save(item);
    }

    public Item getItem(int itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public void deleteItem(int itemId) {
        itemRepository.deleteById(itemId);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public void addQuantity(Item orderItem) {
        Item dbItem = itemRepository.findById(orderItem.getId()).orElse(null);
        if (dbItem != null) {
            dbItem.setQuantity(orderItem.getQuantity() + dbItem.getQuantity());
            updateItem(dbItem);
        }
    }

    public void updateQuantity(Item item) {
        Item dbItem = itemRepository.findById(item.getId()).orElse(null);
        if (dbItem != null) {
            dbItem.setQuantity(item.getQuantity());
            itemRepository.save(dbItem);
        }
    }

    public void subOne(Item orderItem) throws ValidationException {
        Item dbItem = itemRepository.findById(orderItem.getId()).orElse(null);
        if (dbItem != null) {
            dbItem.setQuantity(dbItem.getQuantity() - 1);
//            orderItem.setQuantity(1);
//            orderItem.setUnitPrice(dbItem.getUnitPrice());
//            orderItem.setName(dbItem.getName());
            if (dbItem.getQuantity() < 0) {
                throw new ValidationException("Pas assez de stock sur l'objet " + dbItem.getName());
            }
            updateItem(dbItem);
        }
    }
}
