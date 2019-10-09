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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
//import java.util.HashMap;
//import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ChargeRepositoryTest {
    public final static long ID_1 = 1;
    public final static long PRODUCT_ID = 1;
    public final static long RESERVATION_ID = 1;
    public final static boolean FALSE = false;
    public final static boolean TRUE = true;
    private static final String USER_EMAIL = "email@email.com";
    private static final String USER_NAME = USER_EMAIL;
    private static final String USER_ROLE = "CLIENT";
    private static final double PRODUCT_PRICE = 25.59;

    @Autowired
    private DataSource ds;

    @Autowired
    private ChargeRepository chargeDao;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

//    @Before
//    public void setUp() {
//        jdbcTemplate = new JdbcTemplate(ds);
//        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("product")
//                .usingGeneratedKeyColumns("id");
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "product");
//        Map<String, Object> argsProduct = new HashMap<>();
//        argsProduct.put("id", 1);
//        argsProduct.put("description", "Product1");
//        argsProduct.put("price", 13.99f);
//        argsProduct.put("file", new byte[8]);
//        argsProduct.put("enable", true);
//        simpleJdbcInsert.execute(argsProduct);
//        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("reservation")
//                .usingGeneratedKeyColumns("id");
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "reservation");
//        Map<String, Object> argsReservation = new HashMap<>();
//        argsReservation.put("id", 1);
//        argsReservation.put("start_date", Timestamp.valueOf("2019-09-30 14:00:00"));
//        argsReservation.put("end_date", Timestamp.valueOf("2019-10-11 10:00:00"));
//        argsReservation.put("user_email", "email@email.com");
//        argsReservation.put("room_id", 1);
//        argsReservation.put("hash", "mYzUp3rH4sH");
//        argsReservation.put("is_active", true);
//        argsReservation.put("user_id", 1);
//        simpleJdbcInsert.execute(argsReservation);
//        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("charge")
//                .usingGeneratedKeyColumns("id");
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "charge");
//        Map<String, Object> argsCharge = new HashMap<>();
//        argsCharge.put("id", 1);
//        argsCharge.put("product_id", 1);
//        argsCharge.put("reservation_id", 1);
//        argsCharge.put("delivered", false);
//        simpleJdbcInsert.execute(argsCharge);
//    }

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
////        assertTrue(hasProductId);
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
