//package ar.edu.itba.paw.persistence;
//
//import ar.edu.itba.paw.interfaces.daos.ReservationDao;
//import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
//import ar.edu.itba.paw.models.reservation.Reservation;
//import ar.edu.itba.paw.models.room.Room;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Optional;
//
////@Repository
//public class ReservationRepositoryJDBC extends SimpleRepositoryJDBC<Reservation> implements ReservationDao {
//
//    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, i) -> new Reservation(resultSet);
//
////    @Autowired
//    public ReservationRepositoryJDBC(DataSource dataSource) {
//        super(new NamedParameterJdbcTemplate(dataSource));
//    }
//
//    @Override
//    RowMapper<Reservation> getRowMapper() {
//        return ROW_MAPPER;
//    }
//
//    @Override
//    String getTableName() {
//        return Reservation.TABLE_NAME;
//    }
//
//    @Override
//    SimpleJdbcInsert getJdbcInsert() {
//        return simpleJdbcInsert;
//    }
//
//    @Override
//    public Optional<Reservation> findReservationByHash(String hash) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("hash", hash);
//        List<Reservation> queryResult = jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName() + " r WHERE r.hash = :hash",
//                parameterSource, getRowMapper());
//        return getFirstResultFromList(queryResult);
//    }
//
//    @Override
//    public int updateActive(long reservationId, boolean isActive) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("reservationId", reservationId);
//        parameters.addValue("isActive", isActive);
//        return jdbcTemplateWithNamedParameter.update("UPDATE " + Reservation.TABLE_NAME + " SET "
//                + Reservation.KEY_IS_ACTIVE + " = :isActive WHERE " + Reservation.KEY_ID + " = :reservationId", parameters);
//    }
//
//    @Override
//    public List<RoomReservationDTO> findActiveReservationsByEmail(String userEmail) {
//        return findAllReservationsByUserEmail(userEmail);
//    }
//
//    @Override
//    public List<RoomReservationDTO> findAllReservationsByUserEmail(String userEmail) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("userEmail", userEmail);
//        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName() + " JOIN " + Room.TABLE_NAME + " room ON room.id = "
//                + Reservation.KEY_ROOM_ID + " WHERE " + Reservation.KEY_USER_EMAIL + "  = :userEmail AND " + Reservation.KEY_IS_ACTIVE + " = TRUE ORDER BY "
//                        + Reservation.KEY_START_DATE + " desc"
//                , parameters, getRowMapperWithJoin());
//    }
//
//    private RowMapper<RoomReservationDTO> getRowMapperWithJoin() {
//        return (resultSet, i) -> new RoomReservationDTO(resultSet);
//    }
//
//    @Override
//    public List<Reservation> getAll(){
//       return findAll();
//    }
//
//}
