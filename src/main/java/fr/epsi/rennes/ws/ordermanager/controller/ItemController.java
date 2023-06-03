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

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Item> getAllItems() {
        return itemService.getAll();
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public void createItem(@RequestBody Item item) {
        itemService.create(item);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Item updateItem(@RequestBody Item item) {
        return itemService.update(item);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public void deleteItem(@RequestBody Item item) {
        itemService.delete(item);
    }
  
    @PostMapping(value = "/addAll")
    @ResponseBody
    public void addAllItems(@RequestBody List<Item> items) {
        itemService.addAll(items);
    }

}
