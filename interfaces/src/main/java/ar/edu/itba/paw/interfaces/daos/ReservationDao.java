package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.reservation.Reservation;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ReservationDao extends SimpleDao<Reservation> {

    Optional<Reservation> findReservationByHash(String hash);

    List<Reservation> findAllBetweenDatesAndEmail(Calendar startDate, Calendar endDate, String email);

    int updateActive(long reservationId, boolean b);

    List<Reservation> findActiveReservationByEmail(String userEmail);

}
