package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.generated.Order;
import fr.epsi.rennes.ws.ordermanager.repository.OrderRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ItemService itemService;

    @Transactional
    public Order createOrder(Order order) {
        log.info("Creating order: {}", order.toString());
        try {
            for (Item orderItem : order.getOrderItems()) {
                itemService.subOne(orderItem);
            }
        } catch (ValidationException e) {
            log.error("Error while creating order: {}", e.getMessage());
            throw new ValidationException(e.getMessage());
        }
        return orderRepository.save(order);
    }

    public Order getOrder(int orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (Objects.nonNull(order)) {
            return order;
        }
        throw new ValidationException("Order not found");
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(Order order) {
        removeItems(order, order.getOrderItems());
        orderRepository.delete(order);
    }

    @Transactional
    public Order updateOrder(Order order) {
        Order dbOrder = getOrder(order.getId());
        addMissingItems(dbOrder, order.getOrderItems());
        return orderRepository.save(dbOrder);
    }

    private void addMissingItems(Order dbOrder, List<Item> orderItems) {
        orderItems.forEach(item -> {
                        if (!dbOrder.getOrderItems().contains(item)) {
                            itemService.subOne(item);
                            dbOrder.getOrderItems().add(item);
                        }
                    }
            );
    }

    public void removeItem(Order order, Item item) {
        // quand on enlève un article d'une commande, on le remet dans le stock
        item.setQuantity(1);
        itemService.addQuantity(item);
        order.getOrderItems().remove(item);
    }

    @Transactional
    public void removeItems(Order order, List<Item> items) {
        for (Item item : items) {
            removeItem(order, item);
        }
    }
}
