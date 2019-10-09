package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {
    private static final long ID_1 = 1L;
    private static final String START_DATE = "2019-09-30";
    private static final String END_DATE = "2019-10-10";
    private static final boolean TRUE = true;
    private static final String HASH = "mYzup3rH4sH";
    private static final String FAKE_VALID_EMAIL = "email@email.com";

    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    /**
     * @function_to_test Reservation getReservationByHash(String hash)
     * uses roomDao.findAllFree()
     **/
    @Test
    public void testGetReservationByHash() throws EntityNotFoundException {
        // 1. Setup!
        Reservation reservationToUse = new Reservation(ID_1, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), null, FAKE_VALID_EMAIL, ID_1, ID_1, TRUE, HASH);
        Mockito.when(reservationDao.findReservationByHash(HASH)).thenReturn(java.util.Optional.of(reservationToUse));
        // 2. SUT
        Reservation myReservation = reservationService.getReservationByHash(HASH);
        // 3. Asserts
        Assert.assertNotNull(myReservation);
        Assert.assertEquals(HASH, myReservation.getHash());
    }

    /**
     * @function_to_test void activeReservation(long reservationId)             FUNCTION RETURNS VOID
     * uses reservationDao.updateActive(long reservationId, boolean setTrue)
     **/
    @Test
    public void testActiveReservation() {}

    /**
     * @function_to_test void inactiveReservation(long reservationId)           FUNCTION RETURNS VOID
     * uses reservationDao.updateActive(long reservationId, boolean setFalse)
     **/
    @Test
    public void testInactiveReservation() {}

    /**
     * @function_to_test List<Reservation> getAll()
     * uses reservationDao.getAll()
     **/
    @Test
    public void testGetAll() {
        // 1. Setup!
        Reservation reservation1 = new Reservation(ID_1, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), null, FAKE_VALID_EMAIL, ID_1, ID_1, TRUE, HASH);
        List<Reservation> allReservations = new LinkedList<>();
        allReservations.add(reservation1);
        Mockito.when(reservationDao.getAll()).thenReturn(allReservations);
        // 2. SUT
        List<Reservation> allConfirmedReservations = reservationService.getAll();
        // 3. Asserts
        Assert.assertNotNull(allConfirmedReservations);
        Assert.assertNotNull(allConfirmedReservations.get(0));
        Assert.assertTrue(allConfirmedReservations.get(0).isActive());
    }
}
