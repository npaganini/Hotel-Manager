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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final String PASSWORD = "passwordpassword";
    private static final String USERNAME = "username";

    @Mock
    private UserDao mockDao;
    @Mock
    private ProductDao productDao;
    @Mock
    private ChargeDao chargeDao;
    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(productDao, chargeDao, reservationDao);

    /*
     * function to test: public List<Product> getProducts()
     */
    @Test
    public void testGetProducts() {
//        1. Setup!
//        Mockito.when(mockDao.)
    }

    /*
     * function to test: public List<RoomReservationDTO> findActiveReservation(String userEmail)
     */
    @Test
    public void testFindActiveReservation() {
        //
    }

    /*
     * function to test: public Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId)
     */
    @Test
    public void testCheckProductsPurchasedByUserByReservationId(String userEmail, long reservationId) {
        //
    }

    /*
    * function to test: public boolean addCharge(Charge product)
     */
    @Test
    public void testAddCharge(Charge product) {
        //
    }

//     función a testear: "public User create(final String username)"
//    @Test
//    public void testCreate() {
////        1. Setup!
//        Mockito.when(mockDao.create(Mockito.eq(USERNAME), Mockito.eq(PASSWORD))).thenReturn(new Product(1, USERNAME, PASSWORD));
////        2. "ejercito" la class under test
//        Optional<Product> maybeUser = userService.create(USERNAME, PASSWORD);
////        3. Asserts!
//        Assert.assertNotNull(maybeUser);
//        Assert.assertTrue(maybeUser.isPresent());
//        Assert.assertEquals(USERNAME, maybeUser.get().getUsername());
//        Assert.assertEquals(PASSWORD, maybeUser.get().getPassword());
//    }

//    @Test
//    public void testCreateEmptyPassword() {
////        1. Setup!
////        2. "ejercito" la class under test
////        Optional<User> maybeUser = userService.create(USERNAME, "");
//        // 3. Asserts!
//        Assert.assertNotNull(maybeUser);
//        Assert.assertFalse(maybeUser.isPresent());
//    }
//    @Test
//    public void testCreateAlreadyExists() {
////        1. Setup!
////        Mockito.when(mockDao.findByUsername(Mockito.eq(USERNAME))).thenReturn(Optional.of(new User(1, USERNAME, PASSWORD)));
////        2. "ejercito" la class under test
////        Optional<User> maybeUser = userService.create(USERNAME, PASSWORD);
////        3. Asserts!
//        Assert.assertNotNull(maybeUser);
//        Assert.assertFalse(maybeUser.isPresent());
//    }
}
