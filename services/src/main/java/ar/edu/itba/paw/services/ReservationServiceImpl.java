package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        return reservationDao.findReservationByHash(hash);
    }

    @Override
    public void activeReservation(long reservationId) {
        reservationDao.updateActive(reservationId, true);
    }

    @Override
    public void inactiveReservation(long reservationId) {
        reservationDao.updateActive(reservationId, false);
    }

    @Override

    public List<Reservation> getAll(){return reservationDao.getAll();}
}
