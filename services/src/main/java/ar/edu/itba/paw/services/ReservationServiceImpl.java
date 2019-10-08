package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Request;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationDao reservationDao;

    @Autowired
    public ReservationServiceImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    public Reservation getReservationByHash(String hash) throws EntityNotFoundException {
        LOGGER.debug("About to get reservation with hash " + hash);
        return reservationDao.findReservationByHash(hash).orElseThrow(
                () -> new EntityNotFoundException("Reservation of hash " + hash + " not found"));
    }

    @Override
    public void activeReservation(long reservationId) throws RequestInvalidException {
        LOGGER.debug("About to set reservation with id " + reservationId + " to active");
        if (reservationDao.findById(reservationId).orElseThrow(RequestInvalidException::new).isActive()) {
            throw new RequestInvalidException();
        }
        reservationDao.updateActive(reservationId, true);
    }

    @Override
    public void inactiveReservation(long reservationId) throws RequestInvalidException {
        LOGGER.debug("About to set reservation with id " + reservationId + " to unactivated");
        if (!reservationDao.findById(reservationId).orElseThrow(RequestInvalidException::new).isActive()) {
            throw new RequestInvalidException();
        }
        reservationDao.updateActive(reservationId, false);
    }

    @Override
    public List<Reservation> getAll() {
        LOGGER.debug("About to get all the confirmed reservations");
        return reservationDao.getAll();
    }

    @Override
    public Reservation getReservationById(long reservationId) throws EntityNotFoundException {
        LOGGER.debug("About to get reservation with id " + reservationId);
        return reservationDao.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Reservation with id " + reservationId+  " not found"));
    }
}
