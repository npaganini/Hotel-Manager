package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.dtos.RatingDTO;
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
    public RatingDTO getHotelRating() {
        LOGGER.debug("About to get the general hotel rating.");
        return new RatingDTO(reservationDao.getHotelRating());
    }

    @Override
    public PaginatedDTO<Calification> getAllHotelRatings(int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.debug("Getting a list of all hotel ratings...");
        return reservationDao.getAllRatings(page, pageSize);
    }

    @Override
    public RatingDTO getRoomRating(long roomId) {
        LOGGER.debug("Getting the room's rating for room with id: " + roomId);
        return new RatingDTO(reservationDao.getRoomRating(roomId));
    }

    @Override
    public PaginatedDTO<Calification> getAllRoomRatings(long roomId, int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.debug("Getting a list of all the room's ratings for room with id: " + roomId);
        return reservationDao.getRatingsByRoom(roomId, page, pageSize);
    }
}
