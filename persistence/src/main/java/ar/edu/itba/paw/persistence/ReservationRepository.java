package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReservationRepository extends SimpleRepository<Reservation> implements ReservationDao {

    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, i) -> new Reservation(resultSet);

    @Autowired
    public ReservationRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY," +
                "startDate TIMESTAMP," +
                "endDate TIMESTAMP," +
                "userEmail VARCHAR(100)," +
                "roomId INTEGER," +
                "FOREIGN KEY (roomId) REFERENCES room(id))");
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
