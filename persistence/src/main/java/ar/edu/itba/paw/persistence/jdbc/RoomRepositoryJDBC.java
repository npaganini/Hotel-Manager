//package ar.edu.itba.paw.persistence;
//
//import ar.edu.itba.paw.interfaces.daos.RoomDao;
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
//import java.time.LocalDate;
//import java.util.List;
//
////@Repository
//public class RoomRepositoryJDBC extends SimpleRepositoryJDBC<Room> implements RoomDao {
//
//    private static final RowMapper<Room> ROW_MAPPER = (resultSet, i) -> new Room(resultSet);
//
////    @Autowired
//    public RoomRepositoryJDBC(DataSource dataSource) {
//        super(new NamedParameterJdbcTemplate(dataSource));
//    }
//
//    @Override
//    public List<RoomReservationDTO> findAllBetweenDatesOrEmailAndSurname(String startDate, String endDate, String email) {
//        String andCriteria = getAndCriteriaToFindRooms(startDate, endDate, email);
//        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + Reservation.TABLE_NAME + " res JOIN "
//                        + Room.TABLE_NAME + " r ON res.room_id = r.id " + andCriteria,
//                getParametersToUse(startDate, endDate, email), getRowMapperWithJoin());
//    }
//
//    @Override
//    public List<Room> findAllFreeBetweenDates(String startDate, String endDate) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("startDate", LocalDate.parse(startDate));
//        parameterSource.addValue("endDate", LocalDate.parse(endDate));
//        return jdbcTemplateWithNamedParameter.query("select * from room r where not exists (select res.room_id " +
//                        "SELECT * FROM reservation res WHERE res.room_id = r.id AND ((:startDate <= res.end_date AND :startDate " +
//                        ">= res.start_date) OR (:endDate >= res.start_date AND :endDate <= res.end_date)))",
//                parameterSource, getRowMapper());
//    }
//
//    @Override
//    public List<Room> findAllFree() {
//        return jdbcTemplateWithNamedParameter.getJdbcTemplate().query("SELECT * FROM " + getTableName() +
//                " r WHERE " + Room.KEY_FREE_NOW + " = true", getRowMapper());
//    }
//
//    private String getAndCriteriaToFindRooms(String startDate, String endDate, String email) {
//        StringBuilder andSentendeBuilder = new StringBuilder();
//        if (startDate != null && startDate.length() > 0)
//            andSentendeBuilder.append("WHERE res.start_date >= :startDate ");
//        if (endDate != null && endDate.length() > 0) andSentendeBuilder.append("AND res.end_date <= :endDate ");
//        if (email != null && email.length() > 0) andSentendeBuilder.append("AND res.user_email = :email");
//        return andSentendeBuilder.toString();
//    }
//
//    private MapSqlParameterSource getParametersToUse(String startDate, String endDate, String email) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        if (startDate != null && startDate.length() > 0) parameters.addValue("startDate", LocalDate.parse(startDate));
//        if (endDate != null && endDate.length() > 0) parameters.addValue("endDate", LocalDate.parse(endDate));
//        if (email != null && email.length() > 0) parameters.addValue("email", email);
//        return parameters;
//    }
//
//    private RowMapper<RoomReservationDTO> getRowMapperWithJoin() {
//        return ((resultSet, i) -> new RoomReservationDTO(resultSet));
//    }
//
//
//    @Override
//    public int reserveRoom(long roomId) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("roomId", roomId);
//        return jdbcTemplateWithNamedParameter.update("UPDATE " + Room.TABLE_NAME + " SET " + Room.KEY_FREE_NOW + " " +
//                "= false WHERE id = :roomId ", parameterSource);
//    }
//
//    public void freeRoom(long roomId) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("roomId", roomId);
//        jdbcTemplateWithNamedParameter.update("UPDATE " + Room.TABLE_NAME + " SET " + Room.KEY_FREE_NOW + " " +
//                "= true WHERE id = :roomId ", parameterSource);
//    }
//
//    public List<RoomReservationDTO> getRoomsReservedActive() {
//        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + Reservation.TABLE_NAME + " res JOIN "
//                + Room.TABLE_NAME + " r ON res.room_id = r.id ", getRowMapperWithJoin());
//    }
//
//    @Override
//    RowMapper<Room> getRowMapper() {
//        return ROW_MAPPER;
//    }
//
//    @Override
//    String getTableName() {
//        return Room.TABLE_NAME;
//    }
//
//    @Override
//    SimpleJdbcInsert getJdbcInsert() {
//        return simpleJdbcInsert;
//    }
//}
