package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;

import java.time.LocalDate;
import java.util.List;

public class RoomRepository extends SimpleRepository<Room> implements RoomDao {
    @Override
    public List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Room> findByRoomType(RoomType roomType) {
        return null;
    }
}
