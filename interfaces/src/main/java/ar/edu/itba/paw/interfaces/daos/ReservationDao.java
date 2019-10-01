package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.reservation.Reservation;

import java.security.cert.Extension;

public interface ReservationDao extends SimpleDao<Reservation> {
    Reservation findLastReservationByUserId(long userID);

    Reservation findReservationByHash(String hash);
}
