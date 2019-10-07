package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDTO;
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

    @InjectMocks
    private ChargeServiceImpl chargeService;


    /**
     * @function_to_test List<ChargeDTO> getAllChargesByReservationId(long reservationId)
     * uses chargeDao.findChargeByReservationHash(reservationId)
     **/
    @Test
    public void testGetAllChargesByReservationId() throws SQLException {
        // 1. Setup!
        Product product1 = new Product(PRODUCT_NAME_1, PRODUCT_PRICE_1);
        Reservation reservationValid = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        Charge charge1 = new Charge(ID_1, ID_1);
        ChargeDTO chargeDTO = new ChargeDTO(product1, charge1, reservationValid);
        List<ChargeDTO> chargeDTOList = new LinkedList<>();
        chargeDTOList.add(chargeDTO);
        Mockito.when(chargeDao.findChargeByReservationHash(ID_1)).thenReturn(chargeDTOList);
        // 2. SUT
        List<ChargeDTO> userReservations = chargeService.getAllChargesByReservationId(ID_1);
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
     * uses chargeDao.sumCharge(reservationId)
     **/
    @Test
    public void testSumCharge() {
        // 1. Setup!
        Mockito.when(chargeDao.sumCharge(ID_1)).thenReturn(TOTAL);
        // 2. SUT
        double total = chargeService.sumCharge(ID_1);
        // 3. Asserts
        Assert.assertEquals(TOTAL, total, DELTA_DIFFERENCE);
    }
}
