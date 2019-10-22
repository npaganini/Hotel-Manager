package ar.edu.itba.paw.models.user;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements SqlObject {
    public final static String KEY_ID = "id";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_USERNAME = "username";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_ROLE = "role";

    public final static String TABLE_NAME = "users";

    // tableName_keyID_seq
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "reservationOwner")
    private List<Reservation> myReservations;

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
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

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> userToMap = new HashMap<>();
        userToMap.put(KEY_ID, getId());
        userToMap.put(KEY_EMAIL, getEmail());
        userToMap.put(KEY_USERNAME, getUsername());
        userToMap.put(KEY_ROLE, getRole().toString());
        userToMap.put(KEY_PASSWORD, getPassword());
        return userToMap;
    }
}
