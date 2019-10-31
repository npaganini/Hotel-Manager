package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    List<Reservation> findAllBetweenDatesAndEmail(String startDate, String endDate, String email);

    int reserveRoom(long roomId);

    List<Room> findAllFree();

    void freeRoom(long roomId);

    List<Reservation> getRoomsReservedActive();

    List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate);
}
