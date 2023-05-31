
package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

//disabled SerializationFeature.FAIL_ON_EMPTY_BEANS

@Getter
@Setter
@Entity(name = "orders")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 71293691263123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected int id;

    @Column(name = "customer_name", nullable = false)
    protected String customerName;

    @ManyToMany
    @JoinTable(
            name = "commande_article",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    protected List<Item> orderItems;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }

    public List<Item> getOrderItems() {
        if (orderItems == null) {
            orderItems = new java.util.ArrayList<>();
        }
        return this.orderItems;
    }
}
