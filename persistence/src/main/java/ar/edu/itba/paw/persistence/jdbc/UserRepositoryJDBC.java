/*package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import ar.edu.itba.paw.persistence.jdbc.SimpleRepositoryJDBC;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

//@Repository
public class UserRepositoryJDBC extends SimpleRepositoryJDBC<User> implements UserDao {

    private static final RowMapper<User> ROW_MAPPER = (resultSet, i) -> new User(resultSet);

//    @Autowired
    public UserRepositoryJDBC(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
    }

    @Override
    RowMapper<User> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    String getTableName() {
        return User.TABLE_NAME;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", userEmail);
        List<User> result = jdbcTemplateWithNamedParameter
                .query("SELECT * FROM " + getTableName() + " u WHERE " + User.KEY_EMAIL + " = :email",
                        mapSqlParameterSource, getRowMapper());
        return getFirstResultFromList(result);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", username);
        return getFirstResultFromList(jdbcTemplateWithNamedParameter.query("SELECT * FROM " + User.TABLE_NAME +
                " WHERE " + User.KEY_USERNAME + " = :username", mapSqlParameterSource, getRowMapper()));
    }
}
*/