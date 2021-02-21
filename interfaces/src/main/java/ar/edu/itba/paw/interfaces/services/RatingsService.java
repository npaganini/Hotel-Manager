package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.dtos.CalificationResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.dtos.RatingDTO;

public interface RatingsService {
    RatingDTO getHotelRating();

    PaginatedDTO<CalificationResponse> getAllHotelRatings(int page, int pageSize);

    RatingDTO getRoomRating(int roomId) throws EntityNotFoundException;

    PaginatedDTO<CalificationResponse> getAllRoomRatings(int roomId, int page, int pageSize) throws EntityNotFoundException;
}
