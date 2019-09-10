package ar.edu.itba.paw.models.user;

import ar.edu.itba.paw.models.SqlObject;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
public class User implements SqlObject {

    public final static String KEY_ID = "id";
    public final static String KEY_EMAIL = "email";

    public final static String TABLE_NAME = "users";

    private long id;
    private String email;

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.email = resultSet.getString(KEY_EMAIL);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> userToMap = new HashMap<>();
        userToMap.put("id", getId());
        userToMap.put("email", getEmail());
        return userToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
