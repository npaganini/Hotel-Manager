package ar.edu.itba.paw.persistance.test;

import ar.edu.itba.paw.models.user.User;
import ar.edu.itba.paw.persistance.config.TestConfig;
import ar.edu.itba.paw.persistence.UserRepository;
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
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryTest {
    private static final String USER_EMAIL = "email@email.com";
    private static final String USER_NAME = USER_EMAIL;
    private static final String USER_ROLE = "CLIENT";

    @Autowired
    private DataSource ds;

    @Autowired
    private UserRepository userDao;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
        Map<String, Object> args = new HashMap<>();
        args.put("id", 1);
        args.put("email", USER_EMAIL);
        args.put("username", USER_NAME);
        args.put("password", "password");
        args.put("role", USER_ROLE);
        simpleJdbcInsert.execute(args);
    }

    // public User findByEmail(String userEmail)
    @Test
    public void testFindByEmail() {
        final Optional<User> user = userDao.findByEmail(USER_EMAIL);
        assertNotNull(user);
        assertEquals(USER_EMAIL, user.get().getEmail());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    // public User findByUsername(String username)
    @Test
    public void testFindByUsername() {
        final Optional<User> user = userDao.findByUsername(USER_NAME);
        assertNotNull(user);
        assertEquals(USER_EMAIL, user.get().getUsername());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }
}
