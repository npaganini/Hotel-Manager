package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.reservation.Reservation;

public interface ReservationService {
    Reservation getReservationByHash(String hash);

    void activeReservation(long reservationId);
}
