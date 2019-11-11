package ar.edu.itba.paw.models.charge;

import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "charge")
public class Charge {
    public final static String KEY_ID = "id";
    public final static String KEY_PRODUCTID = "product_id";
    public final static String KEY_RESERVATIONID = "reservation_id";
    public final static String KEY_DELIVERED = "delivered";

    public final static String TABLE_NAME = "charge";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean delivered;

    @OneToOne
    private Product product;

    @ManyToOne
    private Reservation reservation;

    public Charge(Product product, Reservation reservation) {
        this.product = product;
        this.reservation = reservation;
    }

    public void setProductDelivered() {
        this.delivered = true;
    }
}
