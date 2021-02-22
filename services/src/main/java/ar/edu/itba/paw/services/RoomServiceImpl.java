package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.dtos.ReservationResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.*;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final RoomDao roomDao;
    private final EmailService emailService;
    private final ReservationService reservationService;
    private final ChargeService chargeService;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, EmailService emailService,
                           ReservationService reservationService, ChargeService chargeService) {
        this.roomDao = roomDao;
        this.emailService = emailService;
        this.reservationService = reservationService;
        this.chargeService = chargeService;
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
    @Transactional
    public CheckoutDTO doCheckout(String reservationHash, String uriInfo) throws EntityNotFoundException, RequestInvalidException {
        Reservation reservation = reservationService.getReservationByHash(reservationHash.trim());
        if (!reservation.isActive()) {
            throw new RequestInvalidException();
        }
        LOGGER.debug("Request received to do the check-out on reservation with hash: " + reservationHash);
        freeRoom(reservation.getRoom().getId());
        // FIXME delete this
        List<Charge> charges = chargeService.getAllChargesByReservationId(reservation.getId());
        CheckoutDTO checkoutDTO = new CheckoutDTO(charges,
                charges.size() > 0 ? chargeService.sumCharge(reservation.getId()) : 0d);
        reservationService.inactiveReservation(reservation.getId());
        emailService.sendRateStayEmail(reservationHash, uriInfo.split("rooms")[0]);
        return checkoutDTO;
    }

    @Override
    @Transactional
    public ReservationResponse doCheckin(String reservationHash) throws RequestInvalidException, EntityNotFoundException {
        Reservation reservation = reservationService.getReservationByHash(reservationHash.trim());
        if (reservation.isActive()) {
            throw new RequestInvalidException();
        }
        reserveRoom(reservation.getRoom().getId(), reservation);
        if (reservationService.activeReservation(reservation.getId())) {
            return ReservationResponse.fromReservation(reservation);
        }
        return null;
    }

}
