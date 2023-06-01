package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.generated.Item;
import fr.epsi.rennes.ws.ordermanager.repository.ItemRepository;
import fr.epsi.rennes.ws.ordermanager.repository.OrderRepository;
import fr.epsi.rennes.ws.ordermanager.generated.Order;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;
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
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order addItems(Order order, List<Item> items) {
        order.getOrderItems().addAll(itemRepository.findAllById(items.stream().map(Item::getId).toList()));
        return orderRepository.save(order);
    }
}
