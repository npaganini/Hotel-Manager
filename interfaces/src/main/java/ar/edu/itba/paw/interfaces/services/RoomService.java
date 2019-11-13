package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.util.Calendar;
import java.util.List;

public interface RoomService {

    Room getRoom(long roomID);

    void reserveRoom(long roomId, Reservation reservation);

    void freeRoom(long roomId);

    List<Reservation> findAllBetweenDatesAndEmail(Calendar startDate, Calendar endDate, String email);

    List<Reservation> getRoomsReservedActive();

    Reservation doReservation(long roomId, String userEmail, Calendar startDate, Calendar endDate) throws RequestInvalidException;

    boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate);

    List<Room> findAllFreeBetweenDates(Calendar startDate, Calendar endDate);

    CheckoutDTO doCheckout(String reservationHash) throws EntityNotFoundException, RequestInvalidException;

    void doCheckin(String reservationHash) throws RequestInvalidException, EntityNotFoundException;
}
