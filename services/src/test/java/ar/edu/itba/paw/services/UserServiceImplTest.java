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

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(
            new ProductDao() {
                @Override
                public List<Product> getAllProducts() {
                    List<Product> listOfProducts = new LinkedList<>();
                    listOfProducts.add(new Product("Coca-Cola", 12.50));
                    return listOfProducts;
                }

                @Override
                public int updateProductEnable(long productId, boolean enable) {
                    return 0;
                }

                @Override
                public Product save(Product product) {
                    return null;
                }

                @Override
                public Optional<Product> findById(long id) {
                    return Optional.empty();
                }

                @Override
                public List<Product> findAll() {
                    return null;
                }
            },
            new ChargeDao() {
                @Override
                public boolean addCharge(Charge product) {
                    return false;
                }

                @Override
                public Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId) {
                    return null;
                }

                @Override
                public List<ChargeDTO> findChargeByReservationHash(long reservationId) {
                    return null;
                }

                @Override
                public Charge save(Charge charge) {
                    return null;
                }

                @Override
                public Optional<Charge> findById(long id) {
                    return Optional.empty();
                }

                @Override
                public List<Charge> findAll() {
                    return null;
                }
            },
            new ReservationDao() {
                @Override
                public List<RoomReservationDTO> findAllReservationsByUserEmail(String userEmail) {
                    return null;
                }

                @Override
                public Reservation findReservationByHash(String hash) {
                    return null;
                }

                @Override
                public int updateActive(long reservationId, boolean b) {
                    return 0;
                }

                @Override
                public List<RoomReservationDTO> findActiveReservation(String userEmail) {
                    return null;
                }

                @Override
                public Reservation save(Reservation reservation) {
                    return null;
                }

                @Override
                public Optional<Reservation> findById(long id) {
                    return Optional.empty();
                }

                @Override
                public List<Reservation> findAll() {
                    return null;
                }
            }
    );

    @Mock
    private UserDao mockDao;

    // public List<Product> getProducts()
    @Test
    public void testGetProducts() {
//        1. Setup!
//        Mockito.when(mockDao.)
    }

    // public List<RoomReservationDTO> findActiveReservation(String userEmail)
    @Test
    public void testFindActiveReservation() {
        //
    }

    // public Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId)
    @Test
    public void testCheckProductsPurchasedByUserByReservationId(String userEmail, long reservationId) {
        //
    }

    // public boolean addCharge(Charge product)
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
