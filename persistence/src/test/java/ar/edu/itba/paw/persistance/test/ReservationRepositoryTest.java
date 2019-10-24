package ar.edu.itba.paw.persistance.test;

import ar.edu.itba.paw.persistance.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ReservationRepositoryTest {
    @Autowired
    private DataSource ds;

//    @Autowired
//    private ReservationRepositoryJDBC reservationRepository;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    /**
     * @function_to_test Optional<Reservation> findReservationByHash(String hash)
     */
    @Test
    public void testFindReservationByHash() {}

    /**
     * @function_to_test int updateActive(long reservationId, boolean isActive)
     */
    @Test
    public void testUpdateActive() {}

    /**
     * @function_to_test List<RoomReservationDTO> findActiveReservation(String userEmail)
     */
    @Test
    public void testFindActiveReservation() {}

    /**
     * @function_to_test List<RoomReservationDTO> findAllReservationsByUserEmail(String userEmail)
     */
    @Test
    public void testFindAllReservationsByUserEmail() {}

    /**
     * @function_to_test RowMapper<RoomReservationDTO> getRowMapperWithJoin()
     */
    @Test
    public void testGetRowMapperWithJoin() {}

    /**
     * @function_to_test List<Reservation> getAll()
     */
    @Test
    public void testGetAll() {}
}
