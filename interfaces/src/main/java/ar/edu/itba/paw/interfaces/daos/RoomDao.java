package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomDao extends SimpleDao<Room>{
    List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate);
    List<Room> findByRoomType(RoomType roomType);
}
