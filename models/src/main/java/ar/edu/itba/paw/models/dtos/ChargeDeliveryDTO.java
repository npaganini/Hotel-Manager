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
    private int roomNumber;
    private boolean delivered;
    private String description;

}
