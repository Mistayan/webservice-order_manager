
package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 71293691263123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "customer_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Customer name must contain only letters and spaces")
    private String customerName;

    // un Item peut être commandé plusieurs fois, dans ce cas, il faut le stocker plusieurs fois dans la table de jointure

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> orderItems;

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
