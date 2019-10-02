package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.entities.RoomReservationDao;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    List<RoomReservationDao> findAllFreeBetweenDatesAndType(LocalDate startDate, LocalDate endDate, RoomType roomType);

    List<Room> findByRoomType(RoomType roomType);

    void reservateRoom(long roomId);

    List<Room> findAllFree();
}
