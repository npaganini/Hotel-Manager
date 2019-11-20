package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.*;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Request;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
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
    private final UserService userService;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, UserDao userDao, ReservationDao reservationDao, EmailService emailService,
                           ReservationService reservationService, ChargeService chargeService, UserService userService) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.userDao = userDao;

        this.emailService = emailService;
        this.reservationService = reservationService;
        this.chargeService = chargeService;
        this.userService = userService;
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
        emailService.sendRateStayEmail(reservationHash);
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
