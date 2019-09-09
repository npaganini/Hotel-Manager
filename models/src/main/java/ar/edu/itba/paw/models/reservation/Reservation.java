package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@NoArgsConstructor
public class Reservation {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Charge> extraCharges = new ArrayList<>();
    private String userEmail;
    private long roomId;

}
