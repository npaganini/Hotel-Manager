package ar.edu.itba.paw.models.charge;

import ar.edu.itba.paw.models.SqlObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Charge implements SqlObject {

    public final static String KEY_ID = "id";
    public final static String KEY_PRODUCTID = "productId";
    public final static String KEY_RESERVATIONID = "reservationId";

    public final static String TABLE_NAME = "charge";

    private long id;
    private long productId;
    private long reservationId;

    public Charge(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.productId = resultSet.getLong(KEY_PRODUCTID);
        this.reservationId = resultSet.getLong(KEY_RESERVATIONID);
    }

    public Charge(long productID, long reservationID) {
        this.productId = productID;
        this.reservationId = reservationID;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> chargeToMap = new HashMap<>();
        chargeToMap.put(KEY_ID, getId());
        chargeToMap.put(KEY_PRODUCTID, getProductId());
        chargeToMap.put(KEY_RESERVATIONID, getReservationId());
        return chargeToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
