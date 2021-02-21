package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.charge.Charge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
