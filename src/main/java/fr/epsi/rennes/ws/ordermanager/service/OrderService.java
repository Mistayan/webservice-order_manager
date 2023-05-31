package fr.epsi.rennes.ws.ordermanager.service;

import fr.epsi.rennes.ws.ordermanager.repository.ItemRepository;
import fr.epsi.rennes.ws.ordermanager.repository.OrderRepository;
import fr.epsi.rennes.ws.ordermanager.generated.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private ItemRepository itemRepository;

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        order.getItems().getItem().forEach(item -> {
            item.setOrder(savedOrder);
            itemRepository.save(item);
        });
        return savedOrder;
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

}
