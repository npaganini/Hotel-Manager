package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.entities.RoomReservationDto;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    List<RoomReservationDto> findAllFreeBetweenDatesAndEmail(LocalDate startDate, LocalDate endDate, String email);

    List<Room> findByRoomType(RoomType roomType);

    void reservateRoom(long roomId);

    List<Room> findAllFree();
}
