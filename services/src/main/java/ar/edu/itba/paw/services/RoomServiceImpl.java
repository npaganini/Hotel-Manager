package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final EmailService emailService;


    @Autowired
    public RoomServiceImpl(RoomDao roomDao, UserDao userDao, ReservationDao reservationDao, EmailService emailService) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.userDao = userDao;

        this.emailService = emailService;
    }

    @Override
    public List<Room> getRoomsList() {
        return roomDao.findAllFree();
    }

    @Override
    public Room getRoom(long roomID) {
        return roomDao.findById(roomID).orElse(null);
    }

    @Transactional
    @Override
    public Reservation doReservation(long roomId, String userEmail, LocalDate startDate, LocalDate endDate) throws RequestInvalidException {
        if (!isRoomFreeOnDate(roomId, startDate, endDate))
            throw new RequestInvalidException();
        LOGGER.debug("Looking if there is already a user created with email " + userEmail);
        Optional<User> userOptional = userDao.findByEmail(userEmail);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            LOGGER.debug("There is already an user created with email " + userEmail);
        } else {
            LOGGER.debug("There is not an user created with email " + userEmail + ". So we create one");
            user = userDao.save(new User(userEmail,
                    userEmail,
                    new BCryptPasswordEncoder().encode(userEmail)));
        }
        LOGGER.debug("Saving reservation ");
        Reservation reservation = reservationDao.save(new Reservation());
        LOGGER.debug("Sending email with confirmation of reservation to user");
        emailService.sendConfirmationOfReservation(userEmail, "Reserva confirmada",
                reservation.getHash());
        return reservation;
    }

    @Override
    public void reservateRoom(long roomID, Reservation reservation) {
        roomDao.reservateRoom(roomID);
        emailService.sendCheckinEmail(reservation);
    }

    @Override
    public void freeRoom(long roomId) {
        roomDao.freeRoom(roomId);
    }

    @Override
    public List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email) {
        return roomDao.findAllBetweenDatesAndEmail(startDate, endDate, email);
    }

    @Override
    public List<Room> findAllFreeBetweenDates(String startDate, String endDate) {
        return roomDao.findAllFreeBetweenDates(startDate, endDate);
    }

    @Override
    public List<RoomReservationDTO> getRoomsReservedActive() {
        return roomDao.getRoomsReservedActive();
    }

    @Override
    public boolean isRoomFreeOnDate(long roomId, LocalDate startDate, LocalDate endDate) {
        List<Room> rooms = roomDao.findAllFreeBetweenDates(startDate, endDate)
                .parallelStream().filter(room -> room.getId() == roomId).collect(Collectors.toList());
        return rooms.size() == 1 && rooms.get(0).getId() == roomId;
    }

    @Override
    public List<Room> findAllFreeBetweenDates(String startDate, String endDate) {
        return roomDao.findAllFreeBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

}
