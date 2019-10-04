package ar.edu.itba.paw.models.entities;

import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class RoomReservationDto {
    private Room room;
    private Reservation reservation;

    public RoomReservationDto(ResultSet resultSet) throws SQLException {
        this.room = new Room(resultSet);
        this.reservation = new Reservation(resultSet);
    }
}
