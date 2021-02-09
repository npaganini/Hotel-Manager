package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.dtos.RatingDto;
import ar.edu.itba.paw.models.reservation.Calification;

import java.util.List;

public interface RatingsService {
    RatingDto getHotelRating();

    List<Calification> getAllHotelRatings();

    RatingDto getRoomRating(long roomId);

    List<Calification> getAllRoomRatings(long roomId);
}
