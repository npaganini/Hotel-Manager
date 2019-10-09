package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.reservation.Reservation;
import sun.misc.Request;

import java.util.List;

public interface ReservationService {

    Reservation getReservationByHash(String hash) throws EntityNotFoundException;

    void activeReservation(long reservationId) throws RequestInvalidException;

    void inactiveReservation(long reservationId) throws RequestInvalidException;

    List<Reservation> getAll();

    Reservation getReservationById(long reservationId) throws EntityNotFoundException;
}
