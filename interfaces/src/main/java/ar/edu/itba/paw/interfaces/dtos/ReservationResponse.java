package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor


public class ReservationResponse implements Serializable {
    private Long id;
    private Calendar startDate;
    private Calendar endDate;
    private String userEmail;
    private RoomResponse room;
    private boolean isActive;
    private List<ChargeResponse> charges;
    private List<OccupantResponse> occupants;
    private String hash;
    private Calification calification;

    public static ReservationResponse fromReservation(Reservation reservation) {
        final ReservationResponse rDto = new ReservationResponse();

        rDto.id = reservation.getId();
        rDto.startDate = reservation.getStartDate();
        rDto.endDate = reservation.getEndDate();
        rDto.userEmail = reservation.getUserEmail();
        rDto.room = new RoomResponse(reservation.getRoom().getNumber(), reservation.getRoom().getRoomType(), reservation.getRoom().getId(), reservation.getRoom().isFreeNow());
        rDto.isActive = reservation.getRoom().isFreeNow();
        rDto.charges = reservation
                .getCharges()
                .stream()
                .map(charge -> new ChargeResponse(charge.getId(), charge.isDelivered()))
                .collect(Collectors.toList());
        rDto.occupants = reservation
                .getOccupants()
                .stream()
                .map(occupant -> new OccupantResponse(occupant.getName(), occupant.getSurname()))
                .collect(Collectors.toList());
        rDto.hash = reservation.getHash();
        rDto.calification = reservation.getCalification();

        System.out.println("\n");
        System.out.println(reservation);
        System.out.println("\n");

        return rDto;
    }
}
