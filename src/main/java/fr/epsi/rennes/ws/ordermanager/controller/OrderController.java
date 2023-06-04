package fr.epsi.rennes.ws.ordermanager.controller;

import fr.epsi.rennes.ws.ordermanager.generated.Order;
import fr.epsi.rennes.ws.ordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Slf4j
@EnableWebMvc
@RestController
@RequestMapping(value = "/commandes", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/get")
    @ResponseBody
    public Order getOrder(@RequestBody Order order) {
        log.info("getOrder with id {}", order.getId());
        return orderService.getOrder(order.getId());
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Order> getAllOrders() {
        log.warn("getAllOrders (admin only)");
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        log.info("createOrder {}", order);
        ResponseEntity<String> responseEntity;
        try {
            Order newOrder = orderService.createOrder(order);
            responseEntity = ResponseEntity.ok(("Commande créée avec succès : %s" +
                    "%nNOTEZ CE NUMERO DE BON DE COMMANDE PRECIEUSEMENT !").formatted(newOrder.getId()));
        } catch (Exception e) {
            if (log.isDebugEnabled())
                e.printStackTrace();
            responseEntity = ResponseEntity.status(403).body("Erreur lors de la création de la commande : %s".formatted(e.getMessage()));
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/delete", params = "orderId={orderId}")
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
        ResponseEntity<String> responseEntity;
        try {
            orderService.deleteOrder(order);
            responseEntity = ResponseEntity.ok("Commande supprimée avec succès");
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(403).body("Erreur lors de l'annulation de la commande : %s".formatted(e.getMessage()));
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        ResponseEntity<String> responseEntity;
        try {
            Order newOrder = orderService.updateOrder(order);
            responseEntity = ResponseEntity.ok(newOrder.toString());
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body("Erreur lors de la mise à jour de la commande : %s".formatted(e.getMessage()));
        }
        return responseEntity;
    }
}
