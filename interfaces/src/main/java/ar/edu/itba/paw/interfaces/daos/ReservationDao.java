package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.util.Calendar;
import java.util.Optional;

public interface ReservationDao extends SimpleDao<Reservation> {
    Optional<Reservation> findReservationByHash(String hash);

    boolean updateActive(long reservationId, boolean b);

    PaginatedDTO<Reservation> getActiveReservations(int page, int pageSize);

    PaginatedDTO<Reservation> findActiveReservationsByEmail(String userEmail, int page, int pageSize);

    boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate);

    PaginatedDTO<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname, int page, int pageSize);

    void rateStay(long id, String rate);

    double getHotelRating();

    PaginatedDTO<Calification> getAllRatings(int page, int pageSize);

    double getRoomRating(long roomId);

    PaginatedDTO<Calification> getRatingsByRoom(long roomId, int page, int pageSize);
}
