package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.reservation.Calification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalificationResponse implements Serializable {
    private Calification calification;
    private String userEmail;
    private int roomNumber;
    private Calendar startDate;
    private Calendar endDate;
}
