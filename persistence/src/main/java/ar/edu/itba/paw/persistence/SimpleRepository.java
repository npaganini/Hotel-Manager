package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;
import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.charge.Charge;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;
import java.util.Optional;

import static ar.edu.itba.paw.models.SqlObject.KEY_ID;

public abstract class SimpleRepository<T extends SqlObject> implements SimpleDao<T> {

    final JdbcTemplate jdbcTemplate;
    final SimpleJdbcInsert simpleJdbcInsert;

    SimpleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate)
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
        List<T> resultSet = jdbcTemplate.query("SELECT FROM " + getTableName() + " WHERE id = ?", getRowMapper(), id);
        return resultSet.size() > 0 ? Optional.of(resultSet.get(0)) : Optional.empty();
    }

    abstract RowMapper<T> getRowMapper();

    abstract String getTableName();

    abstract SimpleJdbcInsert getJdbcInsert();
}
