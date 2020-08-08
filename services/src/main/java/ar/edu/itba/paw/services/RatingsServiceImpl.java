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
        return reservationDao.getHotelRating();
    }

    @Override
    public List<Calification> getAllHotelRatings() {
        return reservationDao.getAllRatings();
    }

    @Override
    public double getRoomRating(long roomId) {
        return reservationDao.getRoomRating(roomId);
    }

    @Override
    public List<Calification> getAllRoomRatings(long roomId) {
        return reservationDao.getRatingsByRoom(roomId);
    }
}
