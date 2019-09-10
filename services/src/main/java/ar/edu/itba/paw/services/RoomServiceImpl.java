package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.*;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomServiceImpl implements RoomService {

    private final ChargeDao chargeDao;
    private final ProductDao productDao;
    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    private final UserDao userDao;

    private List<Room> roomsList;

    @Autowired
    public RoomServiceImpl(ChargeDao chargeDao, ProductDao productDao, ReservationDao reservationDao, RoomDao roomDao, UserDao userDao) {
        this.productDao = productDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
        this.reservationDao = reservationDao;
        this.chargeDao = chargeDao;
    }

    public List<Room> getRoomsList() {
        return roomsList;
    }

    @Override
    public Room getRoom(long roomID) {
        return null;
    }
}
