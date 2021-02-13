package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.dtos.ReservationResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.util.Calendar;
import java.util.List;

public interface RoomService {
    void reserveRoom(long roomId, Reservation reservation) throws RequestInvalidException, EntityNotFoundException;

    void freeRoom(long roomId);

    List<Room> findAllFreeBetweenDates(Calendar startDate, Calendar endDate);

    CheckoutDTO doCheckout(String reservationHash) throws EntityNotFoundException, RequestInvalidException;

    ReservationResponse doCheckin(String reservationHash) throws RequestInvalidException, EntityNotFoundException;
}
