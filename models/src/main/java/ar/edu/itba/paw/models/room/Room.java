package ar.edu.itba.paw.models.room;

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
public class Room {

    public static final String KEY_ID = "id";
    public static final String KEY_ROOM_TYPE = "room_type";
    public static final String KEY_FREE_NOW = "is_free_now";
    public static final String KEY_NUMBER = "number";

    public static final String TABLE_NAME = "room";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_ID)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = KEY_ROOM_TYPE)
    private RoomType roomType;

    @Column(nullable = false, name = KEY_FREE_NOW)
    private boolean freeNow;

    @Column(nullable = false)
    private int number; // > 0

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private List<Reservation> reservations;

    public Room(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.roomType = RoomType.valueOf(resultSet.getString(KEY_ROOM_TYPE));
        this.freeNow = resultSet.getBoolean(KEY_FREE_NOW);
        this.number = resultSet.getInt(KEY_NUMBER);
    }

}
