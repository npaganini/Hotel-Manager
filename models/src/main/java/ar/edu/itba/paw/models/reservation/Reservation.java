package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation implements SqlObject {

    public final static String KEY_ID = "id";
    public final static String KEY_START_DATE = "start_date";
    public final static String KEY_END_DATE = "end_date";
    public final static String KEY_USER_EMAIL = "user_email";
    public final static String KEY_ROOM_ID = "room_id";
    public final static String KEY_HASH = "hash";
    public final static String KEY_IS_ACTIVE = "is_active";
    public final static String KEY_USER_ID = "user_id";

    public final static String TABLE_NAME = "reservation";

    // tableName_keyID_seq
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_id_seq")
    @SequenceGenerator(sequenceName = "reservation_id_seq", name = "reservation_id_seq", allocationSize = 1)
    private Long id;

    // Hibernate version supports LocalDate
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 100)
    private String userEmail;

    private Long roomId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room assignedRoom;

    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User reservationOwner;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationToCharge")
    private List<Charge> extraCharges = new ArrayList<>();

    @Column(nullable = false)
    private String hash = getRandomString();

    public Reservation(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.startDate = resultSet.getDate(KEY_START_DATE).toLocalDate();
        this.endDate = resultSet.getDate(KEY_END_DATE).toLocalDate();
        this.userEmail = resultSet.getString(KEY_USER_EMAIL);
        this.roomId = resultSet.getLong(KEY_ROOM_ID);
        this.userId = resultSet.getLong(KEY_USER_ID);
        this.hash = resultSet.getString(KEY_HASH);
        this.isActive = resultSet.getBoolean(KEY_IS_ACTIVE);
    }

    public Reservation(long roomId, String userEmail, LocalDate startDate, LocalDate endDate, long userId) {
        this.startDate = startDate;
        this.roomId = roomId;
        this.endDate = endDate;
        this.userEmail = userEmail;
        this.userId = userId;
    }

    public Reservation(Room room, String userEmail, LocalDate startDate, LocalDate endDate, User user) {
        this.startDate = startDate;
        this.assignedRoom = room;
        this.roomId = room.getId();
        this.endDate = endDate;
        this.userEmail = userEmail;
        this.userId = user.getId();
        this.reservationOwner = user;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> reservationToMap = new HashMap<>();
        reservationToMap.put(KEY_ID, getId());
        reservationToMap.put(KEY_START_DATE, getStartDate());
        reservationToMap.put(KEY_END_DATE, getEndDate());
        reservationToMap.put(KEY_USER_EMAIL, getUserEmail());
        reservationToMap.put(KEY_ROOM_ID, getRoomId());
        reservationToMap.put(KEY_USER_ID, getUserId());
        reservationToMap.put(KEY_HASH, getHash());
        reservationToMap.put(KEY_IS_ACTIVE, isActive());
        return reservationToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "Reservation id: " + id + " for " + startDate + " to " + endDate + ". User: " + userEmail + " and hash " + hash;
    }

    private String getRandomString() {
        return String.format("%06d", new Random().nextInt(999999));
    }

}
