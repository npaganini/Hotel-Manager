package ar.edu.itba.paw.persistance.test;

import ar.edu.itba.paw.persistance.config.TestConfig;
import ar.edu.itba.paw.persistence.RoomRepositoryJDBC;
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
import java.util.HashMap;
import java.util.Map;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RoomRepositoryTest {
    private final static long ID_1 = 1;
    private static final String TYPE_DOUBLE = "DOUBLE";
    private final static boolean TRUE = true;
    private final static int ROOM_NUMBER = 105;

    @Autowired
    private DataSource ds;

    @Autowired
    private RoomRepositoryJDBC roomRepository;

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
    }

    /**
     * @function_to_test List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email)
     */
    @Test
    public void testFindAllBetweenDatesAndEmail() {}

    /**
     * @function_to_test List<Room> findAllFreeBetweenDates(String startDate, String endDate)
     */
    @Test
    public void testFindAllFreeBetweenDates() {}

    /**
     * @function_to_test List<Room> findAllFree()
     */
    @Test
    public void testFindAllFree() {}

    /**
     * @function_to_test String getAndCriteriasToFindRooms(String startDate, String endDate, String email)
     */
    @Test
    public void testGetAndCriteriasToFindRooms() {}

    /**
     * @function_to_test MapSqlParameterSource getParametersToUse(String startDate, String endDate, String email)
     */
    @Test
    public void testGetParametersToUse() {}

    /**
     * @function_to_test RowMapper<RoomReservationDTO> getRowMapperWithJoin()
     */
    @Test
    public void testGetRowMapperWithJoin() {}

    /**
     * @function_to_test int reservateRoom(long roomId)
     */
    @Test
    public void testReservateRoom() {}

    /**
     * @function_to_test void freeRoom(long roomId)
     */
    @Test
    public void testFreeRoom() {}

    /**
     * @function_to_test List<RoomReservationDTO> getRoomsReservedActive()
     */
    @Test
    public void testGetRoomsReservedActive() {}

}
