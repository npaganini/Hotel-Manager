package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.RoomService;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomServiceImpl implements RoomService {

    private List<Room> roomsList ;




    public List<Room> getRoomsList() {
        return roomsList;
    }

    @Override
    public Room getRoom(long roomID) {
        return null;
    }
}
