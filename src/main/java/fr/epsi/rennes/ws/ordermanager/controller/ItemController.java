package fr.epsi.rennes.ws.ordermanager.controller;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/articles", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/get}")
    @ResponseBody
    public Item getItem(@RequestBody Item item) {
        return itemService.getById(item.getId());
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Item> getAllItems() {
        return itemService.getAll();
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        ResponseEntity<String> responseEntity;
        try {
            itemService.create(item);
            responseEntity = new ResponseEntity<>("Article %s Créé".formatted(item.getName()), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while creating item " + item, e);
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> updateItem(@RequestBody Item item) {
        ResponseEntity<String> responseEntity;
        try {
            itemService.update(item);
            responseEntity = new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while updating item " + item, e);
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteItem(@RequestBody Item item) {
        ResponseEntity<String> responseEntity;
        try {
            itemService.delete(item);
            responseEntity = new ResponseEntity<>("Article désactivé", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting item " + item, e);
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping(value = "/addAll")
    @ResponseBody
    public ResponseEntity<String> addAllItems(@RequestBody List<Item> items) {
        ResponseEntity<String> responseEntity;
        try {
            itemService.addAll(items);
            responseEntity = new ResponseEntity<>("Articles Créés avec succès.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding items " + items, e);
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
