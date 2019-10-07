package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public void doReservation(Reservation reserva) {
        LOGGER.debug("About to do reservation with hash " + reserva.getHash());
        LOGGER.debug("Looking if there is already a user created with email " + reserva.getUserEmail());
        User user = userDao.findByEmail(reserva.getUserEmail());
        if (user != null) {
            LOGGER.debug("There is already an user created with email " + reserva.getUserEmail());
            reserva.setUserId(user.getId());
        } else {
            LOGGER.debug("There is not an user created with email " + reserva.getUserEmail() + ". So we create one");
            reserva.setUserId(userDao.save(new User(reserva.getUserEmail(),
                    reserva.getUserEmail(),
                    new BCryptPasswordEncoder().encode(reserva.getUserEmail()))).getId());
        }
        LOGGER.debug("Saving reservation with hash " + reserva.getHash());
        reservationDao.save(reserva);
        LOGGER.debug("Sending email with confirmation of reservation to user");
        emailService.sendConfirmationOfReservation(reserva.getUserEmail(), "Reserva confirmada",
                "Su reserva ha sido confirmada! " +
                        "Hash de la reserva: " + reserva.getHash() + "\n Credenciales: \n username: "
                        + reserva.getUserEmail() + "\n password: " + reserva.getUserEmail());
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
    public List<RoomReservationDTO> findAllBetweenDatesAndEmail(LocalDate startDate, LocalDate endDate, String email) {
        return roomDao.findAllBetweenDatesAndEmail(startDate, endDate, email);
    }

    @Override
    public List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate) {
        return roomDao.findAllFreeBetweenDates(startDate, endDate);
    }

}
