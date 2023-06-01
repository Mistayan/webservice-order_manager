package fr.epsi.rennes.ws.ordermanager.controller;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping(value = "/{itemId}", produces = "application/json")
    @ResponseBody
    public Item getItem(@PathVariable int itemId) {
        return itemService.getItem(itemId);
    }

    @PostMapping(value = "/new", consumes = "application/json")
    @ResponseBody
    public void createItem(@RequestBody Item item) {
        itemService.createItem(item);
    }

    @PostMapping(value = "/{itemId}/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Item updateItem(@PathVariable int itemId,@RequestBody Item item) {
        if (item.getId() != itemId) {
            log.error("Item ID mismatch");
            return null;
        }
        return itemService.updateItem(item);
    }

    @DeleteMapping(value = "/{itemId}/delete")
    @ResponseBody
    public void deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
    }

    @PostMapping(value = "/addList", consumes = "application/json")
    @ResponseBody
    public void addAllItems(@RequestBody List<Item> items) {
        for (Item item : items) {
            createItem(item);
        }
    }

    @PutMapping("/stock/update")
    @ResponseBody
    public void updateQuantity(@RequestBody Item item) {
        itemService.updateQuantity(item);
    }

    @PutMapping("/stock/add")
    @ResponseBody
    public void addQuantity(@RequestBody Item item) {
        itemService.addQuantity(item);
    }
}
