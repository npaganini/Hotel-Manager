package ar.edu.itba.paw.services;

import org.springframework.stereotype.Component;

@Component
public class RoomService {

    private String[] roomsList = {"101", "102", "201", "202"};

    public String[] getRoomsList() {
        return roomsList;
    }
}
