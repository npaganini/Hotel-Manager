package ar.edu.itba.paw.models;

import java.util.Map;

public interface SqlObject {
    Map<String, Object> toMap();
    void setId(long id);
}
