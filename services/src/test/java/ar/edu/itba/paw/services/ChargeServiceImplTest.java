package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
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

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChargeServiceImplTest {

    public static final int NOT_A_ROOM_NUMBER = 404;
    @Mock
    private ChargeDao chargeDao;
    @Mock
    private ReservationDao reservationDao;
    @Mock
    private RoomDao roomDao;

    @InjectMocks
    private ChargeServiceImpl chargeService;

    private final Product product = new Product("Product", 2.01);
    private final Product product2 = new Product("Producto", 3.57);
    private final Room room = new Room(1L, RoomType.DOUBLE, true, 1, new ArrayList<>());
    private final Reservation reservation = new Reservation(room, "email", Calendar.getInstance(), Calendar.getInstance(), null);
    private final Charge charge = new Charge(product, reservation);
    private final List<Charge> charges = new ArrayList<>();

    @Before
    public void init() {
        reservation.setActive(true);
        Mockito.when(reservationDao.findById(1L)).thenReturn(Optional.of(reservation));
        Mockito.when(reservationDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(chargeDao.findById(1L)).thenReturn(Optional.of(charge));
        Mockito.when(chargeDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(chargeDao.updateChargeToDelivered(1L)).thenReturn(1);
        Mockito.when(chargeDao.findChargeByReservationId(1L)).thenReturn(Collections.singletonList(charge));
        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.of(room));
        Charge charge1 = new Charge(product, reservation);
        Charge charge2 = new Charge(product2, reservation);
        charge2.setDelivered(true);
        charges.add(charge1);
        charges.add(charge2);
        Mockito.when(chargeDao.findChargesByRoomNumber(room.getNumber())).thenReturn(charges);
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
        Mockito.when(reservationDao.findById(1L)).thenReturn(Optional.of(reservation));
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

    @Test(expected = RequestInvalidException.class)
    public void setAllValidChargesToDeliveredExceptOneIsAlreadyDeliveredTest() throws RequestInvalidException, EntityNotFoundException {
        // GIVEN
        // setup in init()

        // WHEN
        chargeService.setChargesToDelivered(room.getId());

        // THEN
        // throw exception
    }

    @Test(expected = EntityNotFoundException.class)
    public void setAllValidChargesToDeliveredExceptRoomDoesNotExistTest() throws RequestInvalidException, EntityNotFoundException {
        // GIVEN
        // setup in init()

        // WHEN
        chargeService.setChargesToDelivered(NOT_A_ROOM_NUMBER);

        // THEN
        // throw exception
    }
}
