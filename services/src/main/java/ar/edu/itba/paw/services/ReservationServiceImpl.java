package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Reservation getReservationByHash(String hash) {
        LOGGER.debug("About to get reservation with hash " + hash);
        return reservationDao.findReservationByHash(hash);
    }

    @Override
    public void activeReservation(long reservationId) {
        LOGGER.debug("About to set reservation with id " + reservationId + " to active");
        reservationDao.updateActive(reservationId, true);
    }

    @Override
    public void inactiveReservation(long reservationId) {
        LOGGER.debug("About to set reservation with id " + reservationId + " to unactivated");
        reservationDao.updateActive(reservationId, false);
    }

    @Override
    public List<Reservation> getAll() {
        LOGGER.debug("About to get all the confirmed reservations");
        return reservationDao.getAll();
    }
}
