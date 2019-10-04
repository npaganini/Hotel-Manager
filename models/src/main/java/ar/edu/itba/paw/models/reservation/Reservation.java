package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.charge.Charge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
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


    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Charge> extraCharges = new ArrayList<>();
    private String userEmail;
    private long roomId;
    private long userId;
    private boolean isActive;
    private String hash = UUID.randomUUID().toString();

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
}
