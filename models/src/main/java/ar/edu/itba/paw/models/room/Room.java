package ar.edu.itba.paw.models.room;

import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
@XmlRootElement
public class Room {

    public static final String KEY_ID = "id";
    public static final String KEY_ROOM_TYPE = "room_type";
    public static final String KEY_FREE_NOW = "is_free_now";
    public static final String KEY_NUMBER = "number";

    public static final String NAME = "Room";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = KEY_ROOM_TYPE)
    private RoomType roomType;

    @Column(nullable = false, name = KEY_FREE_NOW)
    private boolean freeNow;

    @Column(nullable = false)
    private int number; // > 0

    // TODO THIS IS NOT BEING USED
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private List<Reservation> reservations;

    @XmlTransient
    public List<Reservation> getReservations() {
        return reservations;
    }
}
