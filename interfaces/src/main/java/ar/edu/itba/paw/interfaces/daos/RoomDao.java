package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.room.Room;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email);

    int reserveRoom(long roomId);

    List<Room> findAllFree();

    void freeRoom(long roomId);

    List<RoomReservationDTO> getRoomsReservedActive();

    List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate);
}
