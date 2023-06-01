package fr.epsi.rennes.ws.ordermanager.controller;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.generated.Order;
import fr.epsi.rennes.ws.ordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EnableWebMvc
@RestController
@RequestMapping(value = "/orders", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{orderId}")
    @ResponseBody
    public Order getOrder(@PathVariable int orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping(value = "/getAll")
    @ResponseBody
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/new")
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping(value ="/{orderId}/delete", params = "orderId={orderId}")
    @ResponseBody
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping(value = "/{orderId}/update")
    @ResponseBody
    public Order updateOrder(@PathVariable int orderId,@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @PostMapping(value = "/addList")
    @ResponseBody
    public void addAllOrders(@RequestBody Iterable<Order> orders) {
        for (Order order : orders) {
            createOrder(order);
        }
    }

    @PutMapping("/{id}/articles")
    public Order addArticlesToCommande(@PathVariable int id, @RequestBody List<Item> items) {
        Order order = orderService.getOrder(id);
        return orderService.addItems(order, items);
    }
}
