package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import ar.edu.itba.paw.models.reservation.Calification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsServiceImpl.class);

    private final ReservationDao reservationDao;

    public RatingsServiceImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    public double getHotelRating() {
        LOGGER.debug("About to get the general hotel rating.");
        return reservationDao.getHotelRating();
    }

    @Override
    public List<Calification> getAllHotelRatings() {
        LOGGER.debug("Getting a list of all hotel ratings...");
        return reservationDao.getAllRatings();
    }

    @Override
    public double getRoomRating(long roomId) {
        LOGGER.debug("Getting the room's rating for room with id: " + roomId);
        return reservationDao.getRoomRating(roomId);
    }

    @Override
    public List<Calification> getAllRoomRatings(long roomId) {
        LOGGER.debug("Getting a list of all the room's ratings for room with id: " + roomId);
        return reservationDao.getRatingsByRoom(roomId);
    }
}
