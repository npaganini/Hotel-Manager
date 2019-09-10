package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ReservationRepository extends SimpleRepository<Reservation> implements ReservationDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, i) -> new Reservation(resultSet);

    @Autowired
    public ReservationRepository(DataSource dataSource, SimpleJdbcInsert simpleJdbcInsert) {
        super(new JdbcTemplate(dataSource));
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    RowMapper<Reservation> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    String getTableName() {
        return Reservation.TABLE_NAME;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return simpleJdbcInsert;
    }
}
