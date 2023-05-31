package fr.epsi.rennes.ws.ordermanager.controller;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.generated.Order;
import fr.epsi.rennes.ws.ordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;

@EnableWebMvc
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{orderId}", produces = "application/json")
    @ResponseBody
    public Order getOrder(@PathVariable int orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @ResponseBody
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/new", consumes = "application/json")
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping(value ="/{orderId}/delete", params = "orderId={orderId}")
    @ResponseBody
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping(value = "/{orderId}/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Order updateOrder(@PathVariable int orderId,@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @PostMapping(value = "/addList", consumes = "application/json")
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
