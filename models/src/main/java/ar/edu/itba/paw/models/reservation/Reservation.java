package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.SqlObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class Reservation implements SqlObject {

    public final static String KEY_ID = "id";
    public final static String KEY_START_DATE = "startDate";
    public final static String KEY_END_DATE = "endDate";
    public final static String KEY_USER_EMAIL = "userEmail";
    public final static String KEY_ROOM_ID = "roomId";

    public final static String TABLE_NAME = "reservation";

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userEmail;
    private long roomId;

    public Reservation(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.startDate = resultSet.getDate(KEY_START_DATE).toLocalDate();
        this.endDate = resultSet.getDate(KEY_END_DATE).toLocalDate();
        this.userEmail = resultSet.getString(KEY_USER_EMAIL);
        this.roomId = resultSet.getLong(KEY_ROOM_ID);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> reservationToMap = new HashMap<>();
        reservationToMap.put(KEY_ID, getId());
        reservationToMap.put(KEY_START_DATE, getStartDate());
        reservationToMap.put(KEY_END_DATE, getEndDate());
        reservationToMap.put(KEY_USER_EMAIL, getUserEmail());
        reservationToMap.put(KEY_ROOM_ID, getRoomId());
        return reservationToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
