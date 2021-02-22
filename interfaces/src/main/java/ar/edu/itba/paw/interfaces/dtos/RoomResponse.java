package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomResponse {
    private int number;
    private RoomType roomType;
    private long id;
    private boolean freeNow;
}
