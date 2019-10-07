package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Date;
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
    public List<RoomReservationDTO> findAllBetweenDatesAndEmail(Date startDate, Date endDate, String email) {
        String andCriterias = getAndCriteriasToFindRooms(startDate, endDate, email);
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + Reservation.TABLE_NAME + " res JOIN "
                        + Room.TABLE_NAME + " r ON res.room_id = r.id " + andCriterias,
                getParametersToUse(startDate, endDate, email), getRowMapperWithJoin());
    }

    @Override
    public List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("startDate", startDate);
        parameterSource.addValue("endDate", endDate);
        return jdbcTemplateWithNamedParameter.query("select * from room r where not exists (select res.room_id " +
                        "from reservation res WHERE res.room_id = r.id AND (res.start_date >= :startDate OR res.end_date <= :endDate))",
                parameterSource, getRowMapper());
    }

    @Override
    public List<Room> findAllFree() {
        return jdbcTemplateWithNamedParameter.getJdbcTemplate().query("SELECT * FROM " + getTableName() +
                " r WHERE " + Room.KEY_FREE_NOW + " = true", getRowMapper());
    }

    private String getAndCriteriasToFindRooms(Date startDate, Date endDate, String email) {
        StringBuilder andSentendeBuilder = new StringBuilder();
        if (startDate != null) andSentendeBuilder.append("WHERE res.start_date >= :startDate ");
        if (endDate != null) andSentendeBuilder.append("AND res.end_date <= :endDate ");
        if (email != null) andSentendeBuilder.append("AND res.user_email = :email");
        return andSentendeBuilder.toString();
    }

    private MapSqlParameterSource getParametersToUse(Date startDate, Date endDate, String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if (startDate != null) parameters.addValue("startDate", startDate);
        if (endDate != null) parameters.addValue("endDate", endDate);
        if (email != null) parameters.addValue("email", email);
        return parameters;
    }

    private RowMapper<RoomReservationDTO> getRowMapperWithJoin() {
        return ((resultSet, i) -> new RoomReservationDTO(resultSet));
    }


    @Override
    public int reservateRoom(long roomId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomId", roomId);
        return jdbcTemplateWithNamedParameter.update("UPDATE " + Room.TABLE_NAME + " SET " + Room.KEY_FREE_NOW + " " +
                "= false WHERE id = :roomId ", parameterSource);
    }

    public void freeRoom(long roomId){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomId", roomId);
        jdbcTemplateWithNamedParameter.update("UPDATE " + Room.TABLE_NAME + " SET " + Room.KEY_FREE_NOW + " " +
                "= true WHERE id = :roomId ", parameterSource);
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
