
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
     * Create an instance of {@link Order }
     * 
     * @return
     *     the new instance of {@link Order }
     */
    public Order createOrder() {
        return new Order();
    }
//
//    /**
//     * Create an instance of {@link OrderItems }
//     *
//     * @return
//     *     the new instance of {@link OrderItems }
//     */
//    public OrderItems createItems() {
//        return new OrderItems();
//    }

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
