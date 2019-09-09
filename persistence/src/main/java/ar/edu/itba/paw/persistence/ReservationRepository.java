package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Reservation;

public class ReservationRepository extends SimpleRepository<Reservation> implements ReservationDao {
}
