package ar.edu.itba.paw.persistance.test;

import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.persistance.config.TestConfig;
import ar.edu.itba.paw.persistence.ChargeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import javax.sql.DataSource;

import java.sql.Timestamp;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ChargeRepositoryTest {
    private final static long ID_1 = 1;
    private final static long PRODUCT_ID = 1;
    private final static long RESERVATION_ID = 1;
    private final static boolean FALSE = false;
    private final static boolean TRUE = true;
    private final static int ROOM_NUMBER = 105;
    private static final String USER_EMAIL = "email@email.com";
    private static final String USER_NAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE = "CLIENT";
    private static final String HASH = "mYzUp3rH4sH";
    private static final double PRODUCT_PRICE = 25.59;
    private static final String TYPE_DOUBLE = "DOUBLE";

    @Autowired
    private DataSource ds;

    @Autowired
    private ChargeRepository chargeDao;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);

        JdbcTestUtils.deleteFromTables(jdbcTemplate, "charge");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "reservation");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "product");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "room");

        // Room
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("room");
        Map<String, Object> argsRoom = new HashMap<>();
        argsRoom.put("id", ID_1);
        argsRoom.put("room_type", TYPE_DOUBLE);
        argsRoom.put("is_free_now", TRUE);
        argsRoom.put("number", ROOM_NUMBER);
        simpleJdbcInsert.execute(argsRoom);

        // User
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users");
        Map<String, Object> argsUser = new HashMap<>();
        argsUser.put("id", ID_1);
        argsUser.put("email", USER_EMAIL);
        argsUser.put("username", USER_NAME);
        argsUser.put("password", USER_PASSWORD);
        argsUser.put("role", USER_ROLE);
        simpleJdbcInsert.execute(argsUser);

        // Product
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product");
        Map<String, Object> argsProduct = new HashMap<>();
        argsProduct.put("id", ID_1);
        argsProduct.put("description", "Product1");
        argsProduct.put("price", 13.99f);
        argsProduct.put("file", new byte[8]);
        argsProduct.put("enable", TRUE);
        simpleJdbcInsert.execute(argsProduct);

        // Reservation
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reservation");
        Map<String, Object> argsReservation = new HashMap<>();
        argsReservation.put("id", ID_1);
        argsReservation.put("start_date", Timestamp.valueOf("2019-09-30 14:00:00"));
        argsReservation.put("end_date", Timestamp.valueOf("2019-10-11 10:00:00"));
        argsReservation.put("user_email", "email@email.com");
        argsReservation.put("room_id", ID_1);
        argsReservation.put("hash", HASH);
        argsReservation.put("is_active", TRUE);
        argsReservation.put("user_id", ID_1);
        simpleJdbcInsert.execute(argsReservation);

        // Charge
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("charge");
        Map<String, Object> argsCharge = new HashMap<>();
        argsCharge.put("id", ID_1);
        argsCharge.put("product_id", ID_1);
        argsCharge.put("reservation_id", ID_1);
        argsCharge.put("delivered", FALSE);
        simpleJdbcInsert.execute(argsCharge);
    }

    /**
     * @function_to_test Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId)
     */
    @Test
    public void testGetAllChargesByUser() {
//        boolean hasProductId = false;
//        Map<Product, Integer> expenses = chargeDao.getAllChargesByUser(USER_EMAIL, ID_1);
//        assertNotNull(expenses);
//        Set<Product> aux = expenses.keySet();
//        Iterator it = aux.iterator();
//        while(!hasProductId && it.hasNext()) {
//            hasProductId = ((Product) it.next()).getId() == ID_1;
//        }
//        assertTrue(hasProductId);
//        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "charge"));
    }

    /**
     * @function_to_test List<ChargeRoomReservationDTO> findChargeByReservationHash(long reservationId)
     */
    @Test
    public void testFindChargeByReservationHash() {}

    /**
     * @function_to_test double sumCharge(long reservationId)
     */
    @Test
    public void testSumCharge() {}

    /**
     * @function_to_test List<ChargeDeliveryDTO> findAllChargesNotDelivered()
     */
    @Test
    public void testFindAllChargesNotDelivered() {}

    /**
     * @function_to_test int updateChargeToDelivered(long chargeId)
     */
    @Test
    public void testUpdateChargeToDelivered() {}

    /**
     * @function_to_test RowMapper<Double> getSumRowMapper()
     */
    @Test
    public void testGetSumRowMapper() {}

    /**
     * @function_to_test RowMapper<ChargeRoomReservationDTO> getRowMapperOfChargeRoomReservationDTO()
     */
    @Test
    public void testGetRowMapperOfChargeRoomReservationDTO() {}

    /**
     * @function_to_test RowMapper<ChargeDeliveryDTO> getRowMapperOfChargeDeliveryDTO()
     */
    @Test
    public void testGetRowMapperOfChargeDeliveryDTO() {}
}
