package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<Room> getRoomsList();

    Room getRoom(long roomID);

    void doReservation(Reservation reserva);

    void reservateRoom(long roomId);

    void freeRoom(long roomId);

    List<RoomReservationDTO> findAllFreeBetweenDatesAndEmail(LocalDate startDate, LocalDate endDate, String email);
}
