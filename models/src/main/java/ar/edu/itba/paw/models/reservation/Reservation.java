package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Getter
@NoArgsConstructor
public class Reservation implements SqlObject {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userEmail;
    private long roomId;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> reservationToMap = new HashMap<>();
        reservationToMap.put("id", getId());
        reservationToMap.put("startDate", getStartDate());
        reservationToMap.put("endDate", getEndDate());
        reservationToMap.put("userEmail", getUserEmail());
        reservationToMap.put("roomId", getRoomId());
        return reservationToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
