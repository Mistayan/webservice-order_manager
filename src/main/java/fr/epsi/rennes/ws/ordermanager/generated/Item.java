
package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;


/**
 * <p>Java class for Item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="Item">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {
    "name",
    "quantity",
    "unitPrice"
})
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @XmlElement(required = true)
    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected int quantity;

    @Column(name = "unit_price", nullable = false)
    protected float unitPrice;

    @ManyToOne
    @JoinColumn(name = "item_list_id")
    private Items items;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public void setOrder(Order savedOrder) {

    }
}
