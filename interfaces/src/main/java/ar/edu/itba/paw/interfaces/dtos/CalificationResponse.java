package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.reservation.Calification;
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CalificationResponse implements Serializable {
    private String calification;

    public static CalificationResponse fromCalification(Calification calification) {
        final CalificationResponse cDto = new CalificationResponse();
        cDto.calification = calification.toString();
        return cDto;
    }
}
