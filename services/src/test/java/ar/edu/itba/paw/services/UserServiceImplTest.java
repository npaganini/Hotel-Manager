package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDTO;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.sql.Date;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final String PRODUCT_NAME_1 = "Coca-Cola";
    private static final String PRODUCT_NAME_2 = "Lays";
    private static final String PRODUCT_NAME_3 = "Snickers";
    private static final float PRODUCT_PRICE_1 = 15.99f;
    private static final float PRODUCT_PRICE_2 = 13.99f;
    private static final int AMOUNT_SINGLE = 1;
    private static final int AMOUNT_TWO = 2;
    private static final int PRODUCT_ARRAY_LIST_SIZE = 3;
    private static final int RESERVATIONS_ARRAY_LIST_SIZE = 1;
    private static final int DELTA_DIFFERENCE = 0;
    private static final long ID_1 = 1L;
    private static final String FAKE_VALID_EMAIL = "email@email.com";
    private static final String START_DATE = "2019-09-30";
    private static final String END_DATE = "2019-10-10";
    private static final int ROOM_NUMBER = 105;
    private static final boolean FALSE = false;
    private static final boolean TRUE = true;

    @Mock
    private UserDao userDao;
    @Mock
    private ProductDao productDao;
    @Mock
    private ChargeDao chargeDao;
    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(productDao, chargeDao, reservationDao);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*
     * function to test: public List<Product> getProducts()
     */
    @Test
    public void testGetProducts() {
        // 1. Setup!
        Product product1 = new Product(PRODUCT_NAME_1, PRODUCT_PRICE_1);
        Product product2 = new Product(PRODUCT_NAME_2, PRODUCT_PRICE_2);
        Product product3 = new Product(PRODUCT_NAME_3, PRODUCT_PRICE_1);
        Mockito.when(productDao.getAllProducts()).thenReturn(new LinkedList<>(Arrays.asList(product1, product2, product3)));
        // 2. SUT
        List<Product> list = userService.getProducts();
        // 3. Asserts
        Assert.assertNotNull(list);
        Assert.assertEquals(PRODUCT_ARRAY_LIST_SIZE, list.size());
        Assert.assertEquals(PRODUCT_PRICE_2, list.get(1).getPrice(), DELTA_DIFFERENCE);
    }

    /*
     * function to test: public List<RoomReservationDTO> findActiveReservation(String userEmail)
     */
    @Test
    public void testFindActiveReservation() {
        // 1. Setup!
        Room room = new Room(ID_1, RoomType.DOUBLE, FALSE, ROOM_NUMBER);
        Reservation reservationValid = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        RoomReservationDTO reservationDTO = new RoomReservationDTO(room, reservationValid);
        List<RoomReservationDTO> list = new LinkedList<>();
        list.add(reservationDTO);
        Mockito.when(reservationDao.findActiveReservation(FAKE_VALID_EMAIL)).thenReturn(list);
        // 2. SUT
        List<RoomReservationDTO> reservationList = userService.findActiveReservation(FAKE_VALID_EMAIL);
        // 3. Asserts
        Assert.assertNotNull(reservationList);
        Assert.assertEquals(RESERVATIONS_ARRAY_LIST_SIZE, reservationList.size());
        Assert.assertFalse(reservationList.get(0).getRoom().isFreeNow());
    }

    /*
     * function to test: public Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId)
     * uses chargeDao.getAllChargesByUser()
     */
    @Test
    public void testCheckProductsPurchasedByUserByReservationId(String userEmail, long reservationId) {
        // 1. Setup!
        Product product1 = new Product(PRODUCT_NAME_1, PRODUCT_PRICE_1);
        Product product2 = new Product(PRODUCT_NAME_2, PRODUCT_PRICE_2);
        Integer i1 = AMOUNT_SINGLE;
        Integer i2 = AMOUNT_TWO;
        Map<Product, Integer> productsMap = new HashMap<>();
        productsMap.put(product1, i1);
        productsMap.put(product2, i2);
        Mockito.when(chargeDao.getAllChargesByUser(FAKE_VALID_EMAIL, ID_1)).thenReturn(productsMap);
        // 2. SUT
        Map<Product, Integer> expenses = userService.checkProductsPurchasedByUserByReservationId(FAKE_VALID_EMAIL, ID_1);
        // 3. Asserts
        Assert.assertNotNull(expenses);
        Assert.assertTrue(expenses.containsKey(product1));
        Assert.assertTrue(expenses.containsValue(AMOUNT_TWO));
    }

    /*
    * function to test: public boolean addCharge(Charge product)
    * uses chargeDao.addCharge()
    */
    @Test
    public void testAddCharge() {
        // 1. Setup!
        Charge charge = new Charge(ID_1, ID_1);
        Mockito.when(chargeDao.addCharge(charge)).thenReturn(TRUE);
        // 2. SUT
        boolean chargeAdded = userService.addCharge(charge);
        // 3. Asserts
        Assert.assertTrue(chargeAdded);
    }
}
