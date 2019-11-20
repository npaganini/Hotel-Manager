package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.OccupantDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final OccupantDao occupantDao;
    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public ReservationServiceImpl(OccupantDao occupantDao, ReservationDao reservationDao, RoomDao roomDao, UserService userService, EmailService emailService) {
        this.occupantDao = occupantDao;
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.userService = userService;
        this.emailService = emailService;
	
    }

    @Override
    public Reservation getReservationByHash(String hash) throws EntityNotFoundException {
        LOGGER.debug("About to get reservation with hash " + hash);
        return reservationDao.findReservationByHash(hash.trim()).orElseThrow(
                () -> new EntityNotFoundException("Reservation of hash " + hash + " not found"));
    }

    @Override
    public void activeReservation(long reservationId) throws RequestInvalidException {
        LOGGER.debug("About to set reservation with id " + reservationId + " to active");
        if (reservationDao.findById(Math.toIntExact(reservationId)).orElseThrow(RequestInvalidException::new).isActive()) {
            throw new RequestInvalidException();
        }
        reservationDao.updateActive(reservationId, true);
    }

    @Override
    public void inactiveReservation(long reservationId) throws RequestInvalidException {
        LOGGER.debug("About to set reservation with id " + reservationId + " to unactivated");
        if (!reservationDao.findById(Math.toIntExact(reservationId)).orElseThrow(RequestInvalidException::new).isActive()) {
            throw new RequestInvalidException();
        }
        reservationDao.updateActive(reservationId, false);
    }

    @Override
    public List<Reservation> getAll() {
        LOGGER.debug("About to get all the confirmed reservations");
        return reservationDao.findAll();
    }
 
    private boolean isValidDate(Calendar startDate, Calendar endDate) {
        return startDate.getTimeInMillis() < endDate.getTimeInMillis();
    }

    @Override
    public boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate) {
        return isValidDate(startDate, endDate) && reservationDao.isRoomFreeOnDate(roomId, startDate, endDate);
    }

    @Override
    public void registerOccupants(String reservationHash, List<Occupant> listOfOccupantsFromForm) throws EntityNotFoundException {
        Reservation reservation = reservationDao.findReservationByHash(reservationHash)
                .orElseThrow(() -> new EntityNotFoundException("Reservation was not found"));
        listOfOccupantsFromForm
                .parallelStream()
                .peek(occupant -> occupant.setReservation(reservation))
                .forEach(occupantDao::save);
    }

    @Override
    @Transactional
    public void rateStay(String rate, String hash) throws EntityNotFoundException, RequestInvalidException {
        Reservation reservation = reservationDao
                .findReservationByHash(hash)
                .orElseThrow(() -> new EntityNotFoundException("Reservation was not found"));
        if (reservation.getCalification() != null) {
            throw new RequestInvalidException();
        }
        reservationDao.rateStay(reservation.getId(), rate);
    }

    @Transactional
    @Override
    public Reservation doReservation(long roomId, String userEmail, Calendar startDate, Calendar endDate) throws RequestInvalidException {
        if(!isValidDate(startDate, endDate) && !isRoomFreeOnDate(roomId, startDate, endDate))
            throw new RequestInvalidException();
        LOGGER.debug("Looking if there is already a user created with email " + userEmail);
        User user = userService.getUserForReservation(userEmail);
        LOGGER.debug("Getting room...");
        Room room = roomDao.findById(roomId).orElseThrow(javax.persistence.EntityNotFoundException::new);
        LOGGER.debug("Saving reservation...");
        Reservation reservation = reservationDao.save(new Reservation(room, userEmail, startDate, endDate, user));
        LOGGER.debug("Sending email with confirmation of reservation to user");
        emailService.sendConfirmationOfReservation(userEmail, "Reserva confirmada",
                reservation.getHash());
        return reservation;
    }

    @Override
    public List<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname) {
        return reservationDao.findAllBetweenDatesOrEmailAndSurname(startDate, endDate, email, occupantSurname);
    }

    @Override
    public List<Reservation> getRoomsReservedActive() {
        return roomDao.getRoomsReservedActive();
    }


}
