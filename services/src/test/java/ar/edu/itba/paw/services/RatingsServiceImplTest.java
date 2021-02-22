//package ar.edu.itba.paw.services;
//
//import ar.edu.itba.paw.interfaces.daos.ReservationDao;
//import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
//import ar.edu.itba.paw.models.dtos.PaginatedDTO;
//import ar.edu.itba.paw.models.reservation.Calification;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RatingsServiceImplTest {
//    public static final double HOTEL_RATING = 2.8333333333;
//    public static final double ROOM_RATING = 2.8333333333;
//    public static final double DELTA = 0.001;
//    public static final long ROOM_ID = 1L;
//    public static final int PAGE_SIZE = 20;
//    public static final int PAGE = 1;
//    public static final int NON_EXISTENT_ROOM_ID = 13;
//
//    private final List<Calification> listOfRatings = new ArrayList<>();
//
//    @Mock
//    private ReservationDao reservationDao;
//
//    @InjectMocks
//    private RatingsServiceImpl ratingsService;
//
//    @Before
//    public void init() {
//        listOfRatings.add(Calification.AWFUL);
//        listOfRatings.add(Calification.BAD);
//        listOfRatings.add(Calification.BAD);
//        listOfRatings.add(Calification.NORMAL);
//        listOfRatings.add(Calification.GOOD);
//        listOfRatings.add(Calification.EXCELLENT);
//        Mockito.when(reservationDao.getHotelRating()).thenReturn(listOfRatings);
//        Mockito.when(reservationDao.getAllRatings(PAGE, PAGE_SIZE)).thenReturn(new PaginatedDTO<>(listOfRatings, 5L));
//        Mockito.when(reservationDao.getRoomRating(ROOM_ID)).thenReturn(listOfRatings);
//        Mockito.when(reservationDao.getRatingsByRoom(ROOM_ID, PAGE, PAGE_SIZE)).thenReturn(new PaginatedDTO<>(listOfRatings, 5L));
//        Mockito.when(reservationDao.getRoomRating(NON_EXISTENT_ROOM_ID)).thenReturn(new LinkedList<>());
//        Mockito.when(reservationDao.getRatingsByRoom(NON_EXISTENT_ROOM_ID, PAGE, PAGE_SIZE)).thenReturn(new PaginatedDTO<>(new LinkedList<>(), 0));
//    }
//
//    @Test
//    public void getHotelRatingTest() {
//        assertEquals(HOTEL_RATING, ratingsService.getHotelRating().getRating(), DELTA);
//    }
//
//    @Test
//    public void getHotelRatingsTest() {
//        assertEquals(listOfRatings.size(), ratingsService.getAllHotelRatings(PAGE, PAGE_SIZE).getList().size());
//    }
//
//    @Test
//    public void getRoomRatingsTest() throws EntityNotFoundException {
//        assertEquals(ROOM_RATING, ratingsService.getRoomRating(ROOM_ID).getRating(), DELTA);
//    }
//
//    @Test
//    public void getListOfRatingsTest() throws EntityNotFoundException {
//        assertEquals(listOfRatings.size(), ratingsService.getAllRoomRatings(ROOM_ID, PAGE, PAGE_SIZE).getList().size());
//    }
//
//    @Test(expected = EntityNotFoundException.class)
//    public void getNonExistentRoomTest() throws EntityNotFoundException {
//        ratingsService.getRoomRating(NON_EXISTENT_ROOM_ID);
//    }
//
//    @Test(expected = EntityNotFoundException.class)
//    public void getNonExistentRoomRatingsTest() throws EntityNotFoundException {
//        ratingsService.getAllRoomRatings(NON_EXISTENT_ROOM_ID, PAGE, PAGE_SIZE);
//    }
//}
