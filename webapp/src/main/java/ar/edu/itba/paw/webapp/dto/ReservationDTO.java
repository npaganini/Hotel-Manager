package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.glassfish.jersey.server.JSONP;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private Calendar startDate;
    private Calendar endDate;
    private String userEmail;
    private Room room;
    private boolean isActive;
    private List<Charge> charges;
    private List<Occupant> occupants;
    private String hash;
    private Calification calification;

    public static ReservationDTO fromReservation(Reservation reservation) {
        final ReservationDTO rDto = new ReservationDTO();

        rDto.id = reservation.getId();
        rDto.startDate = reservation.getStartDate();
        rDto.endDate = reservation.getEndDate();
        rDto.userEmail = reservation.getUserEmail();
        rDto.room = reservation.getRoom();
        rDto.isActive = reservation.getRoom().isFreeNow();
        rDto.charges = reservation.getCharges();
        rDto.occupants = reservation.getOccupants();
        rDto.hash = reservation.getHash();
        rDto.calification = reservation.getCalification();

        System.out.println("\n");
        System.out.println(reservation);
        System.out.println("\n");

        return rDto;
    }
}
