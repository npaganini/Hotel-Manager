package ar.edu.itba.paw.models.room;

import ar.edu.itba.paw.models.SqlObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class Room implements SqlObject {

    private long id;
    private RoomType roomType;
    private boolean freeNow;
    private int number; // > 0

    public Room(RoomType roomType, int number) {
        this.roomType = roomType;
        this.number = number;
        this.freeNow = false;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> roomToMap = new HashMap<>();
        roomToMap.put("id", getId());
        roomToMap.put("roomType", getRoomType().toString());
        roomToMap.put("freeNow", isFreeNow());
        roomToMap.put("number", getNumber());
        return roomToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
