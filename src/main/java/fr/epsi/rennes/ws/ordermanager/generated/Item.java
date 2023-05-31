
package fr.epsi.rennes.ws.ordermanager.generated;

import jakarta.persistence.*;
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
    protected String name;

    @Column(name = "unit_price", nullable = false)
    protected float unitPrice;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

