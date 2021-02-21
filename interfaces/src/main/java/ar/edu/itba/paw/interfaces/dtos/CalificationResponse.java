package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.reservation.Calification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor


public class CalificationResponse implements Serializable {
    private String calification;

    public static CalificationResponse fromCalification(Calification calification) {
        final CalificationResponse cDto = new CalificationResponse();
        cDto.calification = calification.toString();
        return cDto;
    }
}
