package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.room.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RoomDao extends SimpleDao<Room> {
    List<RoomReservationDTO> findAllBetweenDatesAndEmail(Date startDate, Date endDate, String email);

    List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate);

    int reservateRoom(long roomId);

    List<Room> findAllFree();

    void freeRoom(long roomId);


}
