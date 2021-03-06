package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.dtos.CalificationResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.dtos.RatingDTO;
import ar.edu.itba.paw.models.reservation.Calification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingsServiceImpl implements RatingsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsServiceImpl.class);

    private final ReservationDao reservationDao;

    @Autowired
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
        PaginatedDTO<CalificationResponse> cals = reservationDao.getAllRatings(page, pageSize);
        return new PaginatedDTO<>(cals.getList(), cals.getMaxItems());
    }

    @Override
    public RatingDTO getRoomRating(int roomNumber) throws EntityNotFoundException {
        LOGGER.debug("Getting the room's rating for room with number: " + roomNumber);
        List<Calification> cals = reservationDao.getRoomRating(roomNumber);
        if (cals.size() == 0) {
            LOGGER.debug("Invalid room number: " + roomNumber);
            throw new EntityNotFoundException("Invalid room roomNumber: " + roomNumber);
        }
        return new RatingDTO(getAverageRating(cals));
    }

    @Override
    public PaginatedDTO<CalificationResponse> getAllRoomRatings(int roomNumber, int page, int pageSize) throws EntityNotFoundException {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.debug("Getting a list of all the room's ratings for room with id: " + roomNumber);
        PaginatedDTO<CalificationResponse> cals = reservationDao.getRatingsByRoom(roomNumber, page, pageSize);
        if (cals.getList().size() == 0) {
            LOGGER.debug("Invalid room id: " + roomNumber);
            throw new EntityNotFoundException("Invalid room id: " + roomNumber);
        }
        return new PaginatedDTO<>(cals.getList(), cals.getMaxItems());
    }

    private double getAverageRating(List<Calification> reservations) {
        double rating = 0;
        for (Calification c : reservations) {
            int rated;
            switch (c) {
                case AWFUL:
                    rated = 1;
                    break;
                case BAD:
                    rated = 2;
                    break;
                case NORMAL:
                    rated = 3;
                    break;
                case GOOD:
                    rated = 4;
                    break;
                case EXCELLENT:
                    rated = 5;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            rating += rated;
        }
        return rating / ((double) reservations.size());
    }
}
