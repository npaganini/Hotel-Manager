package ar.edu.itba.paw.models.dtos;

import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoomReservationDTO {
    private Room room;
    private Reservation reservation;

}
