
package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrderRequest }
     * 
     * @return
     *     the new instance of {@link GetOrderRequest }
     */
    public GetOrderRequest createGetOrderRequest() {
        return new GetOrderRequest();
    }

    /**
     * Create an instance of {@link GetOrderResponse }
     * 
     * @return
     *     the new instance of {@link GetOrderResponse }
     */
    public GetOrderResponse createGetOrderResponse() {
        return new GetOrderResponse();
    }

    /**
     * Create an instance of {@link Order }
     * 
     * @return
     *     the new instance of {@link Order }
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link CreateOrderRequest }
     * 
     * @return
     *     the new instance of {@link CreateOrderRequest }
     */
    public CreateOrderRequest createCreateOrderRequest() {
        return new CreateOrderRequest();
    }

    /**
     * Create an instance of {@link CreateOrderResponse }
     * 
     * @return
     *     the new instance of {@link CreateOrderResponse }
     */
    public CreateOrderResponse createCreateOrderResponse() {
        return new CreateOrderResponse();
    }

    /**
     * Create an instance of {@link Items }
     * 
     * @return
     *     the new instance of {@link Items }
     */
    public Items createItems() {
        return new Items();
    }

    /**
     * Create an instance of {@link Item }
     * 
     * @return
     *     the new instance of {@link Item }
     */
    public Item createItem() {
        return new Item();
    }

}
