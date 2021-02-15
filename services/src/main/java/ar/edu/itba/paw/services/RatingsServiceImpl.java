package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.dtos.CalificationResponse;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.dtos.RatingDTO;
import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return new RatingDTO(getAverageRating(reservationDao.getHotelRating()));
    }

    @Override
    public PaginatedDTO<CalificationResponse> getAllHotelRatings(int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.debug("Getting a list of all hotel ratings...");
        PaginatedDTO<Calification> cals = reservationDao.getAllRatings(page, pageSize);
        return new PaginatedDTO<>(cals.getList()
                .stream().map(CalificationResponse::fromCalification).collect(Collectors.toList()),
                cals.getMaxItems());
    }

    @Override
    public RatingDTO getRoomRating(long roomId) {
        LOGGER.debug("Getting the room's rating for room with id: " + roomId);
        return new RatingDTO(getAverageRating(reservationDao.getRoomRating(roomId)));
    }

    @Override
    public PaginatedDTO<CalificationResponse> getAllRoomRatings(long roomId, int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.debug("Getting a list of all the room's ratings for room with id: " + roomId);
        PaginatedDTO<Calification> cals = reservationDao.getRatingsByRoom(roomId, page, pageSize);
        return new PaginatedDTO<>(cals.getList()
                .stream().map(CalificationResponse::fromCalification).collect(Collectors.toList()),
                cals.getMaxItems());
    }

    private double getAverageRating(List<Reservation> reservations) {
        double rating = 0;
        for (Reservation r: reservations) {
            int rated;
            switch (r.getCalification()) {
                case AWFUL: rated = 1; break;
                case BAD: rated = 2; break;
                case NORMAL: rated = 3; break;
                case GOOD: rated = 4; break;
                case EXCELLENT: rated = 5; break;
                default: throw new IllegalArgumentException();
            }
            rating += rated;
        }
        return rating / ((double) reservations.size());
    }
}
