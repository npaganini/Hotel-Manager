package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.util.Calendar;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    List<Reservation> findAllBetweenDatesAndEmail(Calendar startDate, Calendar endDate, String email);

    int reserveRoom(long roomId);

    void freeRoom(long roomId);

    List<Reservation> getRoomsReservedActive();

    List<Room> findAllFreeBetweenDates(Calendar startDate, Calendar endDate);
}
