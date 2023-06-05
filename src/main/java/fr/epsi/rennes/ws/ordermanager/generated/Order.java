package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "orders")
public class Order implements Serializable {

    private static final String PRICE_ERROR_MESSAGE = "Price cannot be negative nor 0";

    @Serial
    private static final long serialVersionUID = 71293691263123L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

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

    @Column(name = "total_price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Quantity cannot be negative nor 0")
    private float totalPrice;

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
