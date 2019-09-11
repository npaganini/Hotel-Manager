package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.*;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.itba.paw.models.room.RoomType.*;

@Component
public class RoomServiceImpl implements RoomService {

    private final ChargeDao chargeDao;
    private final ProductDao productDao;
    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    private final UserDao userDao;


    @Autowired
    public RoomServiceImpl(ProductDao productDao, RoomDao roomDao, UserDao userDao, ReservationDao reservationDao, ChargeDao chargeDao) {
        this.productDao = productDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
        this.reservationDao = reservationDao;
        this.chargeDao = chargeDao;
    }

    private List<Room> roomsList = new ArrayList<Room>();

    Room h1 = new Room(1, SIMPLE, 1, 100);

    Room h2 = new Room(2, DOUBLE, 2, 102);

    Room h5 = new Room(3, SIMPLE, 17, 251);

    Room h3 = new Room(4, TRIPLE, 3, 103);

    Room h4 = new Room(5, SIMPLE, 4, 104);

    public void setRooms() {
        if (roomsList.size() == 0) {
            roomsList.add(h1);
            roomsList.add(h2);
            roomsList.add(h3);
            roomsList.add(h4);
            roomsList.add(h5);
        }
    }

    public List<Room> getRoomsList() {
        if (roomsList.size() == 0) setRooms();
        return roomsList;
    }

    @Override
    public Room getRoom(long roomID) {
        for (Room room : roomsList) {
            if (room.getId() == roomID)
                return room;
        }
        return null;
    }
}
