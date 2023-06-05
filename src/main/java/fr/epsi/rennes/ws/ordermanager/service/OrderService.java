package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.generated.Order;
import fr.epsi.rennes.ws.ordermanager.repository.OrderRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

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
            // On calcule le prix total de la commande au moment de la création
            // pour éviter que le prix ne change si un article est supprimé ou modifié du stock
            float totalPrice = 0;

            // on enlève 1 à la quantité de chaque item de la commande au stock
            // si une quantité devient négative, une exception est levée, et la transaction annulée
            for (Item orderItem : order.getOrderItems()) {
                Item dbItem = itemService.subOne(orderItem);
                totalPrice += dbItem.getUnitPrice();
            }
            order.setTotalPrice(totalPrice);
        } catch (ValidationException e) {
            log.error("Error while creating order: {}", e.getMessage());
            throw new ValidationException(e.getMessage());
        }
        return orderRepository.save(order);
    }

    public Order getOrder(UUID orderId) {
        Future<Optional<Order>> order = orderRepository.getByUUID(orderId);
        try {
            Order dbOrder = order.get().orElse(null);
            if (dbOrder != null) {
                // assemble order items before returning
                assembleOrderItems(dbOrder);
                return dbOrder;
            }
            throw new ValidationException("Order not found");
        } catch (Exception e) {
            log.error("Error while getting order: {}", e.getMessage());
            if (log.isDebugEnabled())
                e.printStackTrace();
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        throw new ValidationException("Order not found");
    }

    private void assembleOrderItems(Order order) {
        // prevent items form being shown multiple times to user
        // concat all duplicate items in order and add 1 quantity for each duplicate
        final List<Item> finalList = new LinkedList<>();
        for (Item orderItem : order.getOrderItems()) {
            if (!finalList.contains(orderItem)) {
                orderItem.setQuantity(1);
                finalList.add(orderItem);
                continue;
            }
            Item itemPojo = finalList.get(finalList.indexOf(orderItem));
            itemPojo.setQuantity(itemPojo.getQuantity() + 1);
        }
        order.setOrderItems(finalList);
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
                        Item dbItem = itemService.subOne(item);
                        dbOrder.getOrderItems().add(dbItem);
                        dbOrder.setTotalPrice(dbOrder.getTotalPrice() + dbItem.getUnitPrice());
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
