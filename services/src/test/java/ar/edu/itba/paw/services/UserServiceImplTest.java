package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    public static final String NEW_EMAIL = "newEmail@email.com";

    @Mock
    private ProductDao productDao;
    @Mock
    private ChargeDao chargeDao;
    @Mock
    private ReservationDao reservationDao;
    @Mock
    private UserDao userDao;
    @Mock
    private EmailService emailService;  // this is being used by test getUserForReservationWithNonExistentUser()

    @InjectMocks
    private UserServiceImpl userService;

    private final Product product = new Product("coke", 2D);
    private final Reservation reservation = new Reservation(null, "email", Calendar.getInstance(), Calendar.getInstance(), null);
    private final Charge charge = new Charge(product, reservation);
    private final User user = new User("email@email.com", "email", "email");
    private final User newUser = new User(NEW_EMAIL, "newEmail", "newEmail");

    @Before
    public void init() {
        product.setId(1L);
        reservation.setId(1L);
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        productIntegerMap.put(product, 4);
        Mockito.when(chargeDao.getAllChargesByUser("email", 1L)).thenReturn(productIntegerMap);
        Mockito.when(chargeDao.getAllChargesByUser("invalidEmail", 2L)).thenReturn(new HashMap<>());
        Mockito.when(chargeDao.save(any(Charge.class))).thenReturn(charge);
        Mockito.when(productDao.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(reservationDao.findById(1L)).thenReturn(Optional.of(reservation));
        Mockito.when(reservationDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(userDao.findByEmail("email")).thenReturn(Optional.of(user));
        Mockito.when(userDao.findByEmail(NEW_EMAIL)).thenReturn(Optional.empty());
        Mockito.when(userDao.save(any(User.class))).thenReturn(newUser);
        // emailService.doNothing()     is default
    }

    @Test
    public void getProductsBoughtByValidReservationIdTest() {
        Map<Product, Integer> productToPrice = userService.checkProductsPurchasedByUserByReservationId("email", 1L);
        assertEquals("Product should have one element", 1, productToPrice.size());
        assertNotNull(productToPrice.get(product));
    }

    @Test
    public void getProductsBoughtByNonValidReservationIdTest() {
        Map<Product, Integer> productToPrice = userService.checkProductsPurchasedByUserByReservationId("invalidEmail", 2L);
        assertEquals("Product should have no element", 0, productToPrice.size());
    }

    @Test
    public void addChargeWithValidProductAndValidReservationId() throws EntityNotFoundException {
        Charge charge = userService.addCharge(1L, 1L);
        assertEquals("Charge's product should have same id", 1L, charge.getProduct().getId());
        assertEquals("Charge's reservation should have same id", 1L, charge.getReservation().getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void addChargeWithInvalidProductAndValidReservationId() throws EntityNotFoundException {
        userService.addCharge(2L, 1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void addChargeWithInvalidProductAndInvalidReservationId() throws EntityNotFoundException {
        userService.addCharge(2L, 2L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void addChargeWithValidProductAndInvalidReservationId() throws EntityNotFoundException {
        userService.addCharge(1L, 2L);
    }

    @Test
    public void getUserForReservationWithAlreadyExistentUser() {
        User user = userService.getUserForReservation("email");
        assertEquals("User already exists, it should have email: email@email.com", "email@email.com", user.getEmail());
    }

    @Test
    public void getUserForReservationWithNonExistentUser() {
        User user = userService.getUserForReservation(NEW_EMAIL);
        assertEquals("User didn't exist, it should now be a new user with email: " + NEW_EMAIL, NEW_EMAIL, user.getEmail());
    }

    @Test
    public void generatePasswordForNewUser() {
        Optional<String> password = Optional.ofNullable(userService.generatePassword());
        assertTrue("Password should be generated", password.isPresent());
        assertEquals("Password should be of specified length", password.get().length(), UserServiceImpl.GENERATED_PASSWORD_LENGTH);
    }
}
