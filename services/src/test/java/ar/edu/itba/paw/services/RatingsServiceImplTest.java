package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Calification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RatingsServiceImplTest {
    public static final double HOTEL_RATING = 7.56;
    public static final double ROOM_RATING = 8.69;
    public static final double DELTA = 0.001;
    public static final long ROOM_ID = 1L;
    public static final int PAGE_SIZE = 20;
    public static final int PAGE = 1;

    private final List<Calification> listOfRatings = new ArrayList<>();

    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private RatingsServiceImpl ratingsService;

    @Before
    public void init() {
        listOfRatings.add(Calification.NORMAL);
        listOfRatings.add(Calification.BAD);
        listOfRatings.add(Calification.NORMAL);
        listOfRatings.add(Calification.GOOD);
        listOfRatings.add(Calification.EXCELLENT);
//        Mockito.when(reservationDao.getHotelRating()).thenReturn(HOTEL_RATING);
        // fixme
//        Mockito.when(reservationDao.getAllRatings(PAGE, PAGE_SIZE)).thenReturn(listOfRatings);
//        Mockito.when(reservationDao.getRoomRating(ROOM_ID)).thenReturn(ROOM_RATING);
        // fixme
//        Mockito.when(reservationDao.getRatingsByRoom(ROOM_ID, PAGE, PAGE_SIZE)).thenReturn(listOfRatings);
    }

    @Test
    public void getHotelRatingTest() {
//        assertEquals(HOTEL_RATING, ratingsService.getHotelRating().getRating(), DELTA);
    }

    @Test
    public void getHotelRatingsTest() {
        // todo
//        assertEquals(listOfRatings.size(), ratingsService.getAllHotelRatings(PAGE, PAGE_SIZE).size());
    }

    @Test
    public void getRoomRatingsTest() {
//        assertEquals(ROOM_RATING, ratingsService.getRoomRating(ROOM_ID).getRating(), DELTA);
    }

    @Test
    public void getListOfRatingsTest() {
        // todo
//        assertEquals(listOfRatings.size(), ratingsService.getAllRoomRatings(ROOM_ID, PAGE, PAGE_SIZE).size());
    }
}
