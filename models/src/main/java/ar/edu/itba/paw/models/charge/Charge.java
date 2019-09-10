package ar.edu.itba.paw.models.charge;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.product.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Charge implements SqlObject {

    public long id;
    public long productId;
    public long reservationId;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> chargeToMap = new HashMap<>();
        chargeToMap.put("id", getId());
        chargeToMap.put("productId", getProductId());
        chargeToMap.put("reservationId", getReservationId());
        return chargeToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
