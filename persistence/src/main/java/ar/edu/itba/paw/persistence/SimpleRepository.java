package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;
import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
        if (getTableName().equals(Product.TABLE_NAME))jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY, " +
                "description VARCHAR(150), " +
                "price DOUBLE PRECISION);");
        if (getTableName().equals(User.TABLE_NAME)) jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY ," +
                "email varchar(100));");
        if(getTableName().equals(Reservation.TABLE_NAME)) jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY, " +
                "roomType VARCHAR(15), " +
                "freeNow BOOLEAN, " +
                "number INTEGER);");
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
