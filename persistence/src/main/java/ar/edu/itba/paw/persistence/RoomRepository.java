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

    @Autowired
    public RoomRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
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
    RowMapper<Room> getRawMapper() {
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
