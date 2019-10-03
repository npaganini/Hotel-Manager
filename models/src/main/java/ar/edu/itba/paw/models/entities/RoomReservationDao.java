package ar.edu.itba.paw.models.entities;

import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class RoomReservationDao {
    private Room room;
    private Reservation reservation;

    public RoomReservationDao(ResultSet resultSet) throws SQLException {
        this.room = new Room(resultSet);
        this.reservation = new Reservation(resultSet);
    }
}
