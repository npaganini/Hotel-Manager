package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ReservationRepository extends SimpleRepository<Reservation> implements ReservationDao {

    @Autowired
    public ReservationRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
    }

    @Override
    RowMapper<Reservation> getRawMapper() {
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
