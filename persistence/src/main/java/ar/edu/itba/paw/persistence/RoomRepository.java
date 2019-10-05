package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RoomRepository extends SimpleRepository<Room> implements RoomDao {

    private static final RowMapper<Room> ROW_MAPPER = (resultSet, i) -> new Room(resultSet);

    @Autowired
    public RoomRepository(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
        if (jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .query("SELECT * FROM " + Room.TABLE_NAME, getRowMapper()).size() == 0)
            getHardcodedRooms().parallelStream().forEach(this::save);
    }

    @Override
    public List<RoomReservationDTO> findAllFreeBetweenDatesAndEmail(LocalDate startDate, LocalDate endDate, String email) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("startDate", startDate);
        parameterSource.addValue("endDate", endDate);
        parameterSource.addValue("email", email);
        return jdbcTemplateWithNamedParameter.query("SELECT r FROM " + getTableName() + " r NATURAL JOIN " +
                        Reservation.TABLE_NAME + " res" +
                        " WHERE res.start_date <= :startDate AND res.end_date >= :endDate AND " + Reservation.KEY_USER_EMAIL
                        + " = :email GROUP BY r.id",
                parameterSource, getRowMapperWithJoin());
    }

    private RowMapper<RoomReservationDTO> getRowMapperWithJoin() {
        return ((resultSet, i) -> new RoomReservationDTO(resultSet));
    }

    @Override
    public List<Room> findByRoomType(RoomType roomType) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomType", roomType);
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName() + " r WHERE r.room_type = :roomType",
                parameterSource, getRowMapper());
    }




    @Override
    public void reservateRoom(long roomId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomId", roomId);
        jdbcTemplateWithNamedParameter.update("UPDATE " + Room.TABLE_NAME + " SET " + Room.KEY_FREE_NOW + " " +
                "= false WHERE id = :roomId ", parameterSource);
    }

    public void freeRoom(long roomId){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomId", roomId);
        jdbcTemplateWithNamedParameter.update("UPDATE " + Room.TABLE_NAME + " SET " + Room.KEY_FREE_NOW + " " +
                "= true WHERE id = :roomId ", parameterSource);
    }

    @Override
    public List<Room> findAllFree() {
        return jdbcTemplateWithNamedParameter.getJdbcTemplate().query("SELECT * FROM " + getTableName() +
                " r WHERE " + Room.KEY_FREE_NOW + " = true", getRowMapper());
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
