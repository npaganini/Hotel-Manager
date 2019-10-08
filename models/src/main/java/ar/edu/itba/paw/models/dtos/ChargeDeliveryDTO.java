package ar.edu.itba.paw.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@Getter
@Setter
public class ChargeDeliveryDTO {

    private long chargeId;
    private boolean delivered;
    private String description;

    public ChargeDeliveryDTO(ResultSet resultSet) throws SQLException {
        this.chargeId = resultSet.getLong("chargeId");
        this.delivered = resultSet.getBoolean("delivered");
        this.description = resultSet.getString("description");
    }
}
