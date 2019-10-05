package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.util.List;

public interface ReservationDao extends SimpleDao<Reservation> {

    List<RoomReservationDTO> findAllReservationsByUserEmail(String userEmail);

    Reservation findReservationByHash(String hash);

    int updateActive(long reservationId, boolean b);

    List<RoomReservationDTO> findActiveReservation(String userEmail);
}
