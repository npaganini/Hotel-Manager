package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ReservationDao extends SimpleDao<Reservation> {

    Optional<Reservation> findReservationByHash(String hash);

    boolean updateActive(long reservationId, boolean b);

    List<Reservation> findActiveReservationsByEmail(String userEmail);

    boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate);

    List<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname);

    void rateStay(long id, String rate);

    double getHotelRating();

    List<Calification> getAllRatings();

    double getRoomRating(long roomId);

    List<Calification> getRatingsByRoom(long roomId);
}
