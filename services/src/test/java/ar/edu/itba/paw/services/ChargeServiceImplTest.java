package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ChargeServiceImplTest {
    private static final long ID_1 = 1L;
    private static final double TOTAL = 45.59;
    private static final int DELTA_DIFFERENCE = 0;
    private static final String FAKE_VALID_EMAIL = "email@email.com";
    private static final String PRODUCT_NAME_1 = "Coca-Cola";
    private static final float PRODUCT_PRICE_1 = 15.99f;
    private static final String START_DATE = "2019-09-30";
    private static final String END_DATE = "2019-10-10";

    @Mock
    private ChargeDao chargeDao;
    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ChargeServiceImpl chargeService;


    /**
     * @function_to_test List<ChargeRoomReservationDTO> getAllChargesByReservationId(long reservationId)
     * uses chargeDao.findChargeByReservationHash(long reservationId)
     **/
    @Test
    public void testGetAllChargesByReservationId() throws Exception {
        // 1. Setup!
        Product product1 = new Product(PRODUCT_NAME_1, PRODUCT_PRICE_1);
        Reservation reservationValid = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        reservationValid.setActive(true);
        Charge charge1 = new Charge(ID_1, ID_1);
        ChargeRoomReservationDTO chargeRoomReservationDTO = new ChargeRoomReservationDTO(product1, charge1, reservationValid);
        List<ChargeRoomReservationDTO> chargeRoomReservationDTOList = new LinkedList<>();
        chargeRoomReservationDTOList.add(chargeRoomReservationDTO);
        Mockito.when(reservationDao.findById(ID_1)).thenReturn(Optional.of(reservationValid));
        Mockito.when(chargeDao.findChargeByReservationHash(ID_1)).thenReturn(chargeRoomReservationDTOList);
        // 2. SUT
        List<ChargeRoomReservationDTO> userReservations = chargeService.getAllChargesByReservationId(ID_1);
        // 3. Asserts
        Assert.assertNotNull(userReservations);
        Assert.assertNotNull(userReservations.get(0));
        Assert.assertNotNull(userReservations.get(0).getCharge());
        Assert.assertEquals(ID_1, userReservations.get(0).getCharge().getProductId());
        Assert.assertNotNull(userReservations.get(0).getProduct());
        Assert.assertEquals(PRODUCT_NAME_1, userReservations.get(0).getProduct().getDescription());
        Assert.assertNotNull(userReservations.get(0).getReservation());
        Assert.assertEquals(FAKE_VALID_EMAIL, userReservations.get(0).getReservation().getUserEmail());
        Assert.assertEquals(START_DATE, userReservations.get(0).getReservation().getStartDate().toString());
    }

    /**
     * @function_to_test double sumCharge(long reservationId)
     * uses chargeDao.sumCharge(long reservationId)
     **/
    @Test
    public void testSumCharge() throws Exception {
        // 1. Setup!
        Charge aCharge = new Charge(ID_1, ID_1);
        Mockito.when(chargeDao.findById(ID_1)).thenReturn(Optional.of(aCharge));
        Mockito.when(chargeDao.sumCharge(ID_1)).thenReturn(TOTAL);
        // 2. SUT
        double total = chargeService.sumCharge(ID_1);
        // 3. Asserts
        Assert.assertEquals(TOTAL, total, DELTA_DIFFERENCE);
    }
}