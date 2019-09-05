package ar.edu.itba.paw.models.room;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Room {

    private long id;
   // private RoomType roomType;
    private boolean free;
    private int number; // > 0

    public Room(/*RoomType roomType*/long id, int number) {
       // this.roomType = roomType;
        this.id = id;
        this.number = number;
        this.free = false;
    }
}
