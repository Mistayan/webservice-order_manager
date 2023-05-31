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
        return itemService.updateItem(item);
    }

    @PostMapping(value = "/addList", consumes = "application/json")
    @ResponseBody
    public void addAllItems(@RequestBody List<Item> items) {
        for (Item item : items) {
            createItem(item);
        }
    }
}
