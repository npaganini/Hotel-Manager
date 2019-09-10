package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class UserRepository extends SimpleRepository<User> implements UserDao {

    @Autowired
    public UserRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
    }

    @Override
    RowMapper<User> getRawMapper() {
        return null;
    }

    @Override
    String getTableName() {
        return null;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return null;
    }
}
