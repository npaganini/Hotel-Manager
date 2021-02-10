package ar.edu.itba.paw.models.charge;

import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

    public final static String NAME = "Charge";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

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

    @XmlTransient
    public Reservation getReservation() {
        return reservation;
    }

}
