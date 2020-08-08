package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.reservation.Calification;

import java.util.List;

public interface RatingsService {
    double getHotelRating();

    List<Calification> getAllHotelRatings();

    double getRoomRating(long roomId);

    List<Calification> getAllRoomRatings(long roomId);
}
