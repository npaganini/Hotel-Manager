package ar.edu.itba.paw.models.room;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Room implements SqlObject {

    public static final String KEY_ID = "id";
    public static final String KEY_ROOM_TYPE = "roomType";
    public static final String KEY_FREE_NOW = "isFreeNow";
    public static final String KEY_NUMBER = "number";

    public static final String TABLE_NAME = "room";

    private long id;
    private RoomType roomType;
    private boolean freeNow;
    private int number; // > 0
    private Reservation reserva;

    public Room(RoomType roomType, int number) {
        this.roomType = roomType;
        this.number = number;
        this.freeNow = true;
    }

    public Room(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.roomType = RoomType.valueOf(resultSet.getString(KEY_ROOM_TYPE));
        this.freeNow = resultSet.getBoolean(KEY_FREE_NOW);
        this.number = resultSet.getInt(KEY_NUMBER);
    }

    public Room(int id, RoomType roomType, int roomFloor, int number) {
        this.roomType = roomType;
        this.number = number;
        this.id = id;
        this.freeNow = true;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> roomToMap = new HashMap<>();
        roomToMap.put("id", getId());
        roomToMap.put("roomType", getRoomType().toString());
        roomToMap.put("freeNow", isFreeNow());
        roomToMap.put("number", getNumber());
        return roomToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public void setReserva(Reservation reserva) {
        this.reserva = reserva;
        this.freeNow = false;
    }
}
