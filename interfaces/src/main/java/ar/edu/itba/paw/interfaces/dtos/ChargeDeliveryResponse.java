package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.charge.Charge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ChargeDeliveryResponse implements Serializable {
    private int roomNumber;
    private long roomId;
    private String description;

    public static ChargeDeliveryResponse fromCharge(Charge charge) {
        final ChargeDeliveryResponse cDto = new ChargeDeliveryResponse();
        cDto.roomNumber = charge.getReservation().getRoom().getNumber();
        cDto.roomId = charge.getReservation().getRoom().getId();
        cDto.description = charge.getProduct().getDescription();
        return cDto;
    }
}
