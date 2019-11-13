package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
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
    private final ReservationService reservationService;
    private final ChargeService chargeService;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, UserDao userDao, ReservationDao reservationDao, EmailService emailService,
                           ReservationService reservationService, ChargeService chargeService) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.userDao = userDao;

        this.emailService = emailService;
        this.reservationService = reservationService;
        this.chargeService = chargeService;
    }

    @Override
    public Room getRoom(long roomID) {
        return roomDao.findById(Math.toIntExact(roomID)).orElse(null);
    }

    @Transactional
    @Override
    public Reservation doReservation(long roomId, String userEmail, Calendar startDate, Calendar endDate) throws RequestInvalidException {
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
        LOGGER.debug("Getting room...");
        Room room = roomDao.findById(Math.toIntExact(roomId)).orElseThrow(EntityNotFoundException::new);
        LOGGER.debug("Saving reservation...");
        Reservation reservation = reservationDao.save(new Reservation(room, userEmail, startDate, endDate));
        reservation.setUser(user);
        LOGGER.debug("Sending email with confirmation of reservation to user");
        emailService.sendConfirmationOfReservation(userEmail, "Reserva confirmada",
                reservation.getHash());
        return reservation;
    }

    @Override
    public void reserveRoom(long roomID, Reservation reservation) {
        roomDao.reserveRoom(roomID);
        emailService.sendCheckinEmail(reservation);
    }

    @Override
    public void freeRoom(long roomId) {
        roomDao.freeRoom(roomId);
    }

    @Override
    public List<Reservation> findAllBetweenDatesAndEmail(Calendar startDate, Calendar endDate, String email) {
        return reservationDao.findAllBetweenDatesAndEmail(startDate, endDate, email);
    }

    @Override
    public List<Reservation> getRoomsReservedActive() {
        return roomDao.getRoomsReservedActive();
    }

    @Override
    public boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate) {
        List<Room> rooms = roomDao.findAllFreeBetweenDates(startDate, endDate)
                .parallelStream().filter(room -> room.getId() == roomId).collect(Collectors.toList());
        return rooms.size() == 1 && rooms.get(0).getId() == roomId;
    }

    @Override
    public List<Room> findAllFreeBetweenDates(Calendar startDate, Calendar endDate) {
        return roomDao.findAllFreeBetweenDates(startDate, endDate);
    }

    @Override
    public CheckoutDTO doCheckout(String reservationHash) throws ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException, RequestInvalidException {
        Reservation reservation = reservationService.getReservationByHash(reservationHash.trim());
        if (!reservation.isActive()) {
            throw new RequestInvalidException();
        }
        LOGGER.debug("Request received to do the check-out on reservation with hash: " + reservationHash);
        freeRoom(reservation.getRoom().getId());
        reservationService.inactiveReservation(reservation.getId());
        return new CheckoutDTO(chargeService.getAllChargesByReservationId(reservation.getId()),
                chargeService.sumCharge(reservation.getId()));
    }

    @Override
    public void doCheckin(String reservationHash) throws RequestInvalidException, ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException {
        Reservation reservation = reservationService.getReservationByHash(reservationHash.trim());
        if (reservation.isActive()) {
            throw new RequestInvalidException();
        }
        reserveRoom(reservation.getRoom().getId(), reservation);
        reservationService.activeReservation(reservation.getId());
    }

}
