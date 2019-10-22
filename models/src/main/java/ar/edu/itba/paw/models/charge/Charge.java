package ar.edu.itba.paw.models.charge;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "charge")
public class Charge implements SqlObject {
    public final static String KEY_ID = "id";
    public final static String KEY_PRODUCTID = "product_id";
    public final static String KEY_RESERVATIONID = "reservation_id";
    public final static String KEY_DELIVERED = "delivered";

    public final static String TABLE_NAME = "charge";

    // tableName_keyID_seq
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charge_id_seq")
    @SequenceGenerator(sequenceName = "charge_id_seq", name = "charge_id_seq", allocationSize = 1)
    private long id;
    private long productId;
    private long reservationId;
    @Column(nullable = false)
    private boolean delivered;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product productPurchased;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Reservation reservationToCharge;

    public Charge(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.productId = resultSet.getLong(KEY_PRODUCTID);
        this.reservationId = resultSet.getLong(KEY_RESERVATIONID);
        this.delivered = resultSet.getBoolean(KEY_DELIVERED);
    }

    public Charge(long productID, long reservationID) {
        this.productId = productID;
        this.reservationId = reservationID;
    }

    public Charge(Product product, Reservation reservation) {
        this.productPurchased = product;
        this.productId = product.getId();
        this.reservationToCharge = reservation;
        this.reservationId = reservation.getId();
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> chargeToMap = new HashMap<>();
        chargeToMap.put(KEY_ID, getId());
        chargeToMap.put(KEY_PRODUCTID, getProductId());
        chargeToMap.put(KEY_RESERVATIONID, getReservationId());
        chargeToMap.put(KEY_DELIVERED, isDelivered());
        return chargeToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
