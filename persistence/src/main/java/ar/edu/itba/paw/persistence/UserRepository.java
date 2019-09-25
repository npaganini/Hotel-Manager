package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository extends SimpleRepository<User> implements UserDao {

    private static final RowMapper<User> ROW_MAPPER = (resultSet, i) -> new User(resultSet);

    @Autowired
    public UserRepository(DataSource dataSource) {
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
    public User findByEmail(String userEmail) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", userEmail);
        List<User> result = jdbcTemplateWithNamedParameter
                .query("SELECT * FROM " + getTableName() + " u WHERE " + User.KEY_EMAIL + " = :email",
                        mapSqlParameterSource, getRowMapper());
        return result.size() > 0 ? result.get(0) : null;
    }
}
