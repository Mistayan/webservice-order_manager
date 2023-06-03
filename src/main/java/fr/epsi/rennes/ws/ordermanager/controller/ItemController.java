package fr.epsi.rennes.ws.ordermanager.controller;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/articles", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/{itemId}")
    @ResponseBody
    public Item getItem(@PathVariable int itemId) {
        return itemService.getById(itemId);
    }

    @PostMapping(value = "/new")
    @ResponseBody
    public void createItem(@RequestBody Item item) {
        itemService.create(item);
    }

    @PostMapping(value = "/{itemId}/update")
    @ResponseBody
    public Item updateItem(@PathVariable int itemId,@RequestBody Item item) {
        if (item.getId() != itemId) {
            log.error("Item ID mismatch");
            return null;
        }
        return itemService.update(item);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public void deleteItem(@RequestBody Item item) {
        itemService.delete(item);
    }
  
    @PostMapping(value = "/addList")
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
