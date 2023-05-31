package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.repository.ItemRepository;
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

    public void updateQuantity(int itemId, int quantity) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
//            item.setQuantity(quantity);
            itemRepository.save(item);
        }
    }
}
