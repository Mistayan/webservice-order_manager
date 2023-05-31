package fr.epsi.rennes.ws.ordermanager.endpoint;

import fr.epsi.rennes.ws.ordermanager.generated.*;
import fr.epsi.rennes.ws.ordermanager.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Définition du Endpoint SOAP pour les commandes.
 *
 */
@AllArgsConstructor
@Endpoint
public class OrderEndpoint {

    private OrderService orderService;

    /**
     * l'utilisateur demande à récupérer une commande spécifique
     * @param request l'identifiant de la commande à récupérer
     * @return la commande demandée
     */
    @PayloadRoot(namespace = "http://localhost:1117/orders", localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        Order order = orderService.getOrder(request.getOrderId());
        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(order);
        return response;
    }


    /**
     * l'utilisateur demande à passer une commande
     * @param request la commande à passer
     * @return l'identifiant de la commande
     */
    @PayloadRoot(namespace = "http://localhost:1117/orders", localPart = "createOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        Order order = orderService.createOrder(request.getOrder());
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderId(order.getId());
        return response;
    }

}