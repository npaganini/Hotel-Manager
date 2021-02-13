package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.dtos.RatingDTO;
import ar.edu.itba.paw.models.reservation.Calification;

import java.util.List;

public interface RatingsService {
    RatingDTO getHotelRating();

    PaginatedDTO<Calification> getAllHotelRatings(int page, int pageSize);

    RatingDTO getRoomRating(long roomId);

    PaginatedDTO<Calification> getAllRoomRatings(long roomId, int page, int pageSize);
}
