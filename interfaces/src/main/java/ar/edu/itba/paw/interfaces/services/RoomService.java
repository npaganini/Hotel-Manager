package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    Room getRoom(long roomID);

    void reserveRoom(long roomId, Reservation reservation);

    void freeRoom(long roomId);

    List<Reservation> findAllBetweenDatesAndEmail(String startDate, String endDate, String email);

    List<Reservation> getRoomsReservedActive();

    Reservation doReservation(long roomId, String userEmail, LocalDate startDate, LocalDate endDate) throws RequestInvalidException;

    boolean isRoomFreeOnDate(long roomId, LocalDate startDate, LocalDate endDate);

    List<Room> findAllFreeBetweenDates(String startDate, String endDate);
}
