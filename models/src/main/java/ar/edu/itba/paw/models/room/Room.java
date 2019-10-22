package ar.edu.itba.paw.models.room;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room implements SqlObject {

    public static final String KEY_ID = "id";
    public static final String KEY_ROOM_TYPE = "room_type";
    public static final String KEY_FREE_NOW = "is_free_now";
    public static final String KEY_NUMBER = "number";

    public static final String TABLE_NAME = "room";

    // tableName_keyID_seq
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_seq")
    @SequenceGenerator(sequenceName = "room_id_seq", name = "room_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(nullable = false)
    private boolean freeNow;

    @Column(nullable = false)
    private int number; // > 0

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "assignedRoom")
    private List<Room> myReservations;

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
