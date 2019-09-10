package ar.edu.itba.paw.models;

import java.util.Map;

public interface SqlObject {

    String KEY_ID = "id";

    Map<String, Object> toMap();
    void setId(long id);
}
