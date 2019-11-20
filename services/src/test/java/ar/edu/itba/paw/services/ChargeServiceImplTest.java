package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChargeServiceImplTest {

    @Mock
    private ChargeDao chargeDao;
    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ChargeServiceImpl chargeService;

    private Product product = new Product("Product", 2d);
    private Room room = new Room(1L, RoomType.DOUBLE, true, 1, new ArrayList<>());
    private Reservation reservation = new Reservation(room, "email", Calendar.getInstance(), Calendar.getInstance(), null);
    private Charge charge = new Charge(product, reservation);

    @Before
    public void init() {
        reservation.setActive(true);
        Mockito.when(reservationDao.findById(1L)).thenReturn(Optional.ofNullable(reservation));
        Mockito.when(reservationDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(chargeDao.findById(1L)).thenReturn(Optional.of(charge));
        Mockito.when(chargeDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(chargeDao.updateChargeToDelivered(1L)).thenReturn(1);
        Mockito.when(chargeDao.findChargeByReservationId(1L)).thenReturn(Collections.singletonList(charge));
    }

    @Test
    public void getAllChargesByValidReservationIdAndReservationActiveTest() throws RequestInvalidException {
        List<Charge> charges = chargeService.getAllChargesByReservationId(1L);
        assertEquals("List of charge should have only one element", 1, charges.size());
    }

    @Test(expected = RequestInvalidException.class)
    public void getAllChargesByInvalidReservationIdTest() throws RequestInvalidException {
        chargeService.getAllChargesByReservationId(2L);
    }

    @Test(expected = RequestInvalidException.class)
    public void getAllChargesByValidReservationIdAndReservationInactiveTest() throws RequestInvalidException {
        reservation.setActive(false);
        Mockito.when(reservationDao.findById(1L)).thenReturn(Optional.ofNullable(reservation));
        chargeService.getAllChargesByReservationId(1L);
    }

    @Test
    public void setValidChargeToDeliveredTest() throws RequestInvalidException, EntityNotFoundException {
        assertEquals("Should update one charge", 1, chargeService.setChargeToDelivered(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void setInvalidChargeToDeliveredTest() throws RequestInvalidException, EntityNotFoundException {
        chargeService.setChargeToDelivered(2L);
    }

    @Test(expected = RequestInvalidException.class)
    public void setValidChargeToDeliveredThatWasAlreadyDeliveredTest() throws RequestInvalidException, EntityNotFoundException {
        charge.setProductDelivered();
        Mockito.when(chargeDao.findById(1L)).thenReturn(Optional.of(charge));
        chargeService.setChargeToDelivered(1L);
    }

}
