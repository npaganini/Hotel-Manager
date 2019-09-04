package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@NoArgsConstructor
public class Reservation {

    private long id;
    private Room room;
    private Calendar startDate;
    private Calendar endDate;
    private List<Charge> extraCharges = new ArrayList<>();

    public Reservation(Room room, Calendar startDate, Calendar endDate) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
