package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.util.Calendar;
import java.util.List;

public interface ReservationService {

    Reservation getReservationByHash(String hash) throws EntityNotFoundException;

    boolean activeReservation(long reservationId) throws RequestInvalidException;

    void inactiveReservation(long reservationId) throws RequestInvalidException;

    List<Reservation> getAll();

    List<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname);

    List<Reservation> getRoomsReservedActive();

    Reservation doReservation(long roomId, String userEmail, Calendar startDate, Calendar endDate) throws RequestInvalidException;

    boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate) throws RequestInvalidException;

    void registerOccupants(String reservation_hash, List<Occupant> listOfOccupantsFromForm) throws EntityNotFoundException;
}
