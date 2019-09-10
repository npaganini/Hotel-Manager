package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class RoomRepository extends SimpleRepository<Room> implements RoomDao {

    private static final RowMapper<Room> ROW_MAPPER = (resultSet, i) -> new Room(resultSet);

    @Autowired
    public RoomRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY, " +
                "roomType VARCHAR(15), " +
                "freeNow BOOLEAN, " +
                "number INTEGER)");
    }

    @Override
    public List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Room> findByRoomType(RoomType roomType) {
        return null;
    }

    @Override
    RowMapper<Room> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    String getTableName() {
        return Room.TABLE_NAME;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return simpleJdbcInsert;
    }
}
