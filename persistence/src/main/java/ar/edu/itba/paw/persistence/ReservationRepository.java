package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReservationRepository extends SimpleRepository<Reservation> implements ReservationDao {

    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, i) -> new Reservation(resultSet);

    @Autowired
    public ReservationRepository(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
        jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                        "id SERIAL PRIMARY KEY," +
                        "start_date TIMESTAMP," +
                        "end_date TIMESTAMP," +
                        "user_email VARCHAR(100), " +
                        "hash VARCHAR(1000), " +
                        "is_active BOOLEAN," +
                        "room_id INTEGER REFERENCES " + Room.TABLE_NAME + "(id)," +
                        "user_id INTEGER REFERENCES " + User.TABLE_NAME + "(id) )");
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

    @Override
    public Reservation findReservationByHash(String hash) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("hash", hash);
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName() + " r WHERE r.hash = :hash",
                parameterSource, getRowMapper()).get(0);
    }

    @Override
    public int updateActive(long reservationId, boolean isActive) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("reservationId", reservationId);
        parameters.addValue("isActive", isActive);
        return jdbcTemplateWithNamedParameter.update("UPDATE " + Reservation.TABLE_NAME + " SET "
                + Reservation.KEY_IS_ACTIVE + " = :isActive WHERE " + Reservation.KEY_ID + " = :reservationId", parameters);
    }

    @Override
    public List<RoomReservationDTO> findActiveReservation(String userEmail) {
        return findAllReservationsByUserEmail(userEmail);
    }

    @Override
    public List<RoomReservationDTO> findAllReservationsByUserEmail(String userEmail) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userEmail", userEmail);
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName() + " JOIN " + Room.TABLE_NAME + " room ON room.id = "
                + Reservation.KEY_ROOM_ID + " WHERE " + Reservation.KEY_USER_EMAIL + "  = :userEmail AND " + Reservation.KEY_IS_ACTIVE + " = TRUE ORDER BY "
                        + Reservation.KEY_START_DATE + " desc"
                , parameters, getRowMapperWithJoin());
    }

    private RowMapper<RoomReservationDTO> getRowMapperWithJoin() {
        return (resultSet, i) -> new RoomReservationDTO(resultSet);
    }

}
