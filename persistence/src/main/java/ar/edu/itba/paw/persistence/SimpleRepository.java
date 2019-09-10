package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;
import ar.edu.itba.paw.models.SqlObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class SimpleRepository<T extends SqlObject> implements SimpleDao<T> {

    private final JdbcTemplate jdbcTemplate;

    protected SimpleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public T save(T t) {
        t.setId(getJdbcInsert().executeAndReturnKey(t.toMap()).longValue());
        return t;
    }

    @Override
    public Optional<T> findById(long id) {
        List<T> resultSet = jdbcTemplate.query("SELECT FROM " + getTableName() + " WHERE id = ?", getRawMapper(), id);
        return resultSet.size() > 0 ? Optional.of(resultSet.get(0)) : Optional.empty();
    }

    abstract RowMapper<T> getRawMapper();
    abstract String getTableName();
    abstract SimpleJdbcInsert getJdbcInsert();
}
