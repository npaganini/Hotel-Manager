package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.util.Calendar;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    int reserveRoom(long roomId);

    void freeRoom(long roomId);

    List<Room> findAllFreeBetweenDates(Calendar startDate, Calendar endDate);
}
