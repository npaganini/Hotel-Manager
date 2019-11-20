package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.OccupantDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final OccupantDao occupantDao;
    private final ReservationDao reservationDao;

    @Autowired
    public ReservationServiceImpl(OccupantDao occupantDao, ReservationDao reservationDao) {
        this.occupantDao = occupantDao;
        this.reservationDao = reservationDao;
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

    @Override
    public void registerOccupants(String reservationHash, List<Occupant> listOfOccupantsFromForm) throws EntityNotFoundException {
        Reservation reservation = reservationDao.findReservationByHash(reservationHash)
                .orElseThrow(() -> new EntityNotFoundException("Reservation was not found"));
        listOfOccupantsFromForm
                .parallelStream()
                .peek(occupant -> occupant.setReservation(reservation))
                .forEach(occupantDao::save);
    }

}
