package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.*;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static ar.edu.itba.paw.models.room.RoomType.*;

@Component
public class RoomServiceImpl implements RoomService {

    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    private final UserDao userDao;


    @Autowired
    public RoomServiceImpl(RoomDao roomDao, UserDao userDao, ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.userDao = userDao;

    }

    public List<Room> getRoomsList() {
        LocalDate lastYear = LocalDate.now().withYear(2018);
        return roomDao.findAllFreeBetweenDates(lastYear, LocalDate.now());
    }

    @Override
    public Room getRoom(long roomID) {
        return roomDao.findById(roomID).orElse(null);
    }

    @Override
    public void doReservation(Reservation reserva) {
        User user = userDao.findByEmail(reserva.getUserEmail());
        if (user != null) {
            reserva.setUserId(user.getId());
        } else {
            reserva.setUserId(userDao.save(new User(reserva.getUserEmail())).getId());
        }
        reservationDao.save(reserva);
        roomDao.reservateRoom(reserva.getRoomId());
    }
}
