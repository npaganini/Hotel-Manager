package ar.edu.itba.paw.models.room;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Room implements SqlObject {

    public static final String KEY_ID = "id";
    public static final String KEY_ROOM_TYPE = "room_type";
    public static final String KEY_FREE_NOW = "is_free_now";
    public static final String KEY_NUMBER = "number";

    public static final String TABLE_NAME = "room";

    private long id;
    private RoomType roomType;
    private boolean freeNow;
    private int number; // > 0

    public Room(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.roomType = RoomType.valueOf(resultSet.getString(KEY_ROOM_TYPE));
        this.freeNow = resultSet.getBoolean(KEY_FREE_NOW);
        this.number = resultSet.getInt(KEY_NUMBER);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> roomToMap = new HashMap<>();
        roomToMap.put(KEY_ID, getId());
        roomToMap.put(KEY_ROOM_TYPE, getRoomType().toString());
        roomToMap.put(KEY_FREE_NOW, isFreeNow());
        roomToMap.put(KEY_NUMBER, getNumber());
        return roomToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
