package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.util.List;

public interface RoomService {

    List<Room> getRoomsList();

    Room getRoom(long roomID);

    void doReservation(Reservation reserva) throws EntityNotFoundException;

    void reservateRoom(long roomId, Reservation reservation);

    void freeRoom(long roomId);

    List<Room> findAllFreeBetweenDates(String startDate, String endDate);

    List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email);

    List<RoomReservationDTO> getRoomsReservedActive();

}
