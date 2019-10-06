package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;
import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import ar.edu.itba.paw.models.user.User;
import ar.edu.itba.paw.models.user.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ar.edu.itba.paw.models.SqlObject.KEY_ID;

public abstract class SimpleRepository<T extends SqlObject> implements SimpleDao<T> {

    final NamedParameterJdbcTemplate jdbcTemplateWithNamedParameter;
    final SimpleJdbcInsert simpleJdbcInsert;

    SimpleRepository(NamedParameterJdbcTemplate jdbcTemplateWithNamedParameter) {
        this.jdbcTemplateWithNamedParameter = jdbcTemplateWithNamedParameter;
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplateWithNamedParameter.getJdbcTemplate())
                        .withTableName(getTableName())
                        .usingGeneratedKeyColumns(KEY_ID);
    }

    @Override
    public T save(T t) {
        t.setId(getJdbcInsert().executeAndReturnKey(t.toMap()).longValue());
        return t;
    }

    @Override
    public Optional<T> findById(long id) {
        List<T> resultSet = jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .query("SELECT * FROM " + getTableName() + " WHERE id = ?", getRowMapper(), id);
        return resultSet.size() > 0 ? Optional.of(resultSet.get(0)) : Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName(), getRowMapper());
    }

    abstract RowMapper<T> getRowMapper();

    abstract String getTableName();

    abstract SimpleJdbcInsert getJdbcInsert();
}
