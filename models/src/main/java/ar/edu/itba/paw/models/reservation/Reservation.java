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
public class Reservation {

    public final static String KEY_ID = "id";
    public final static String KEY_START_DATE = "start_date";
    public final static String KEY_END_DATE = "end_date";
    public final static String KEY_USER_EMAIL = "user_email";
    public final static String KEY_ROOM_ID = "room_id";
    public final static String KEY_HASH = "hash";
    public final static String KEY_IS_ACTIVE = "is_active";
    public final static String KEY_USER_ID = "user_id";

    public final static String TABLE_NAME = "reservation";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hibernate version supports LocalDate
    @Column(nullable = false, name = KEY_START_DATE)
    private LocalDate startDate;
    @Column(nullable = false, name = KEY_END_DATE)
    private LocalDate endDate;

    @Column(length = 100, name = KEY_USER_EMAIL)
    private String userEmail;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

    @Column(nullable = false, name = KEY_IS_ACTIVE)
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<Charge> charges = new ArrayList<>();

    @Column(nullable = false)
    private String hash = getRandomString();

    public String toString() {
        return "Reservation id: " + id + " for " + startDate + " to " + endDate + ". User: " + userEmail + " and hash " + hash;
    }

    private String getRandomString() {
        return String.format("%06d", new Random().nextInt(999999));
    }

}
