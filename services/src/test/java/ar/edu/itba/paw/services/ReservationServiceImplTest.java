package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {
    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final String START_DATE = "2019-09-30";
    private static final String END_DATE = "2019-10-10";
    private static final int ROOM_NUMBER_1 = 101;
    private static final int ROOM_NUMBER_2 = 102;
    private static final boolean TRUE = true;

    @Mock
    private RoomDao roomDao;
    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    /**
     * @function_to_test Reservation getReservationByHash(String hash)
     * uses roomDao.findAllFree()
     **/
    @Test
    public void testGetReservationByHash() {
        // 1. Setup!
//        Room room1 = new Room(ID_1, RoomType.DOUBLE, TRUE, ROOM_NUMBER_1);
//        Room room2 = new Room(ID_2, RoomType.DOUBLE, TRUE, ROOM_NUMBER_2);
//        List<Room> freeRoomsList = new LinkedList<>();
//        freeRoomsList.add(room1);
//        freeRoomsList.add(room2);
//        Mockito.when(roomDao.findAllFree()).thenReturn(freeRoomsList);
        // 2. SUT
        //Reservation myReservation = reservationService.getReservationByHash();
    }

    /**
     * @function_to_test void activeReservation(long reservationId)
     * uses reservationDao.updateActive(long reservationId, boolean setTrue)
     **/
    @Test
    public void testActiveReservation() {
        // 1. Setup!
    }

    /**
     * @function_to_test void inactiveReservation(long reservationId)
     * uses reservationDao.updateActive(long reservationId, boolean setFalse)
     **/
    @Test
    public void testInactiveReservation() {
        // 1. Setup!
    }

    /**
     * @function_to_test List<Reservation> getAll()
     * uses reservationDao.getAll()
     **/
    @Test
    public void testGetAll() {
        // 1. Setup!
    }
}
