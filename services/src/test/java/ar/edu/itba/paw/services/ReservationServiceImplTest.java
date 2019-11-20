package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    public void findExistentReservationByHashTest() {

    }

    @Test
    public void findNonExistentReservationByHashTest() {

    }

    @Test
    public void activeExistentReservationTest() {

    }

    @Test
    public void activeNonExistentReservationTest() {

    }

    @Test
    public void inactiveExistentReservationTest() {

    }

    @Test
    public void inactiveNonExistentReservationTest() {

    }

    @Test
    public void isRoomFreeOnDateWithFreeRoomTest() {

    }

    @Test
    public void isRoomFreeOnDateWithNonFreeRoomTest() {

    }

    @Test
    public void isRoomFreeWithInvalidDateTest() {

    }

    @Test
    public void doReservationWithInvalidDateTest() {

    }

    @Test
    public void doReservationToANonFreeRoomTest() {

    }

    @Test
    public void doReservationWithANonExistentRoomTest() {

    }

    @Test
    public void doReservationWithValidFieldsTest() {

    }

}
