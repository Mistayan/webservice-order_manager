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
@RequestMapping(value = "/commandes", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{orderId}")
    @ResponseBody
    public Order getOrder(@PathVariable int orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping(value ="/delete", params = "orderId={orderId}")
    @ResponseBody
    public void deleteOrder(@RequestBody Order order) {
        orderService.deleteOrder(order);
    }

    @PostMapping(value = "/addAll")
    @ResponseBody
    public void addAllOrders(@RequestBody Iterable<Order> orders) {
        for (Order order : orders) {
            createOrder(order);
        }
    }

    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }
}
