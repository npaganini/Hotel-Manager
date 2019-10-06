package ar.edu.itba.paw.persistance.test;

import ar.edu.itba.paw.models.user.User;
import ar.edu.itba.paw.persistance.config.TestConfig;
import ar.edu.itba.paw.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryTest {
    private static final String USER_EMAIL = "email@email.com";
    private static final String USER_NAME = "username";
    private static final String USER_ROLE = "CLIENT";

    @Autowired
    private DataSource ds;

    @Autowired
    private UserRepository userDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
        jdbcTemplate.execute(
                "INSERT INTO users (id, email, username, password, role)" +
                        "VALUES (1, " + USER_EMAIL + ", " + USER_NAME + ", 'password', " + USER_ROLE + ")");
    }

    // public User findByEmail(String userEmail)
    @Test
    public void testFindByEmail() {
        final User user = userDao.findByEmail(USER_EMAIL);
        assertNotNull(user);
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    // public User findByUsername(String username)
    @Test
    public void testFindByUsername() {
        final User user = userDao.findByUsername(USER_NAME);
        assertNotNull(user);
        assertEquals(USER_EMAIL, user.getUsername());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }
}
