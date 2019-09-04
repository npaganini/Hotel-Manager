package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.RoomService;
import org.springframework.stereotype.Component;

@Component
public class RoomServiceImpl implements RoomService {

    private String[] roomsList = {"101", "102", "201", "202"};

    public String[] getRoomsList() {
        return roomsList;
    }

    @Override
    public String getRoom(long roomID) {
        return null;
    }
}
