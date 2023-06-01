
package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 71293691264123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z1-9() ]+$", message = "Item name must contain only letters, numbers and spaces ( also accepts () )")
    protected String name;

    // price cannot be negative
    @Column(name = "unit_price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price cannot be negative or 0")
    protected float unitPrice;

    // sur une commande, représente la quantité commandée à déduire du stock
    // sur un Item, représente la quantité en stock
    @Column(nullable = false, columnDefinition = "float default 0.0")
    @DecimalMin(value = "0.0", message = "Quantity cannot be negative")
    private int quantity;

    @Override
    public String toString() {
        return "Item{" +
                "id= " + id +
                ", name= '" + name + '\'' +
                ", quantity= " + quantity +
                ", unitPrice= " + unitPrice +
                '}';
    }
}

