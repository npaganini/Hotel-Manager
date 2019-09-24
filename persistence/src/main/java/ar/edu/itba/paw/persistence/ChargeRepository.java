package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ChargeRepository extends SimpleRepository<Charge> implements ChargeDao {

    private final static RowMapper<Charge> ROW_MAPPER = (resultSet, rowNum) -> new Charge(resultSet);

    @Autowired
    public ChargeRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY, " +
                "productId INTEGER REFERENCES product (id), " +
                "reservationId INTEGER REFERENCES reservation (id))");
    }

    @Override
    RowMapper<Charge> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    String getTableName() {
        return Charge.TABLE_NAME;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public boolean addCharge(Charge product) {
        try {
            jdbcTemplate.execute("INSERT INTO " + getTableName()
                    + " (productid, reservationid) "
                    + "VALUES (" + product.getProductId() + ", " + product.getReservationId() + ");"
            );
        } catch (Exception sqlE) {
            return false;
        }
        return true;
    }
}
