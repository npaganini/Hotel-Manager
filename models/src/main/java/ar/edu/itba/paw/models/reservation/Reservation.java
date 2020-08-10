package ar.edu.itba.paw.models.reservation;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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

    public final static String NAME = "reservation";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

    @Column(nullable = false, name = KEY_START_DATE)
    private Calendar startDate;
    @Column(nullable = false, name = KEY_END_DATE)
    private Calendar endDate;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<Occupant> occupants = new ArrayList<>();

    @Column(nullable = false)
    private String hash = getRandomString();

    @Enumerated(EnumType.STRING)
    private Calification calification;

    public Reservation(Room room, String userEmail, Calendar startDate, Calendar endDate, User user) {
        this.room = room;
        this.userEmail = userEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public String toString() {
        return "Reservation id: " + id + " for " + startDate + " to " + endDate + ". User: " + userEmail + " and hash " + hash;
    }

    private String getRandomString() {
        return String.format("%06d", new Random().nextInt(999999));
    }

}
