package ar.edu.itba.paw.models.user;

import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable    {
    public final static String KEY_ID = "id";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_USERNAME = "username";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_ROLE = "role";

    public final static String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> reservations;

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt(KEY_ID);
        this.email = resultSet.getString(KEY_EMAIL);
        this.role = UserRole.valueOf(resultSet.getString(KEY_ROLE));
        this.password = resultSet.getString(KEY_PASSWORD);
        this.username = resultSet.getString(KEY_USERNAME);
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = UserRole.CLIENT;
    }
}
