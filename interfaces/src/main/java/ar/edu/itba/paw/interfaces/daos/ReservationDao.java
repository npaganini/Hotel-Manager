package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.security.cert.Extension;
import java.util.List;

public interface ReservationDao extends SimpleDao<Reservation> {
    Reservation findLastReservationByUserId(long userID);

    List<Reservation> findAllReservationsByUserId(long userID);

    Reservation findReservationByHash(String hash);

}
