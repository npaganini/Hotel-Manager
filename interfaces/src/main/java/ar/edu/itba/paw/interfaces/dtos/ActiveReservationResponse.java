package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActiveReservationResponse {
    private Long reservationId;
    private Calendar startDate;
    private Calendar endDate;
    private String userEmail;
    private RoomType roomType;
    private int roomNumber;
    private String hash;


    public static ActiveReservationResponse fromReservation(final Reservation reservation) {
        ActiveReservationResponse activeReservationResponse = new ActiveReservationResponse();
        activeReservationResponse.setReservationId(reservation.getId());
        activeReservationResponse.setStartDate(reservation.getStartDate());
        activeReservationResponse.setEndDate(reservation.getEndDate());
        activeReservationResponse.setUserEmail(reservation.getUserEmail());
        activeReservationResponse.setRoomNumber(reservation.getRoom().getNumber());
        activeReservationResponse.setRoomType(reservation.getRoom().getRoomType());
        activeReservationResponse.setHash(reservation.getHash());
        return activeReservationResponse;
    }
}
