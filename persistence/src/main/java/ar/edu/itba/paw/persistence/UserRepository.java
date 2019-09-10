package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository extends SimpleRepository<User> implements UserDao {

    private static final RowMapper<User> ROW_MAPPER = (resultSet, i) -> new User(resultSet);

    @Autowired
    public UserRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
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
}
