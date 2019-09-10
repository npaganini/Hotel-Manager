package ar.edu.itba.paw.models.user;

import ar.edu.itba.paw.models.SqlObject;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class User implements SqlObject {

    private long id;
    private String email;

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
