package ar.edu.itba.paw.models.user;

import ar.edu.itba.paw.models.SqlObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class User implements SqlObject {

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public final static String KEY_ID = "id";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_USERNAME = "username";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_ROLE = "role";

    public final static String TABLE_NAME = "users";

    private long id;
    private String email;
    private String password;
    private String username;
    private UserRole role;

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
