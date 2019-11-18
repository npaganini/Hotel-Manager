package ar.edu.itba.paw.persistence.jdbc;

/*import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.dtos.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class ChargeRepositoryJDBC extends SimpleRepositoryJDBC<Charge> implements ChargeDao {

    private final static RowMapper<Charge> ROW_MAPPER = (resultSet, rowNum) -> new Charge(resultSet);

//    @Autowired
    public ChargeRepositoryJDBC(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
    }

    @Override
    RowMapper<Charge> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    String getTableName() {
        return Charge.TABLE_NAME;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("email", userEmail);
        parameterSource.addValue("reservationId", reservationId);
        return jdbcTemplateWithNamedParameter.query(
                "SELECT description, price, count(p) FROM charge c JOIN product p " +
                        "ON c.product_id = p.id JOIN reservation r ON r.id = c.reservation_id " +
                        "WHERE r.user_email = :email AND r.id = :reservationId group by p.id",
                parameterSource, rs -> {
                    Map<Product, Integer> ans = new HashMap<>();
                    while (rs.next()) {
                        Product p = new Product(rs.getString(1), rs.getFloat(2));
                        ans.put(p, rs.getInt(3));
                    }
                    return ans;
                });
    }

    @Override
    public List<Charge> findChargeByReservationId(long reservationId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("reservationId", reservationId);
        return jdbcTemplateWithNamedParameter
                .query("SELECT * FROM " + Charge.TABLE_NAME + " c JOIN " +
                        Product.TABLE_NAME + " p ON c.product_id = p.id JOIN " + Reservation.TABLE_NAME +
                        " res ON res.id = c.reservation_id WHERE res.id = :reservationId", parameters, getRowMapperOfChargeRoomReservationDTO());
    }

    @Override
    public double sumCharge(long reservationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("reservationId", reservationId);
        List<Double> queryResult = jdbcTemplateWithNamedParameter.query("SELECT sum(p.price) FROM charge c JOIN product p " +
                        "ON p.id = c.product_id JOIN reservation r ON c.reservation_id = r.id WHERE r.id = :reservationId GROUP BY r.id",
                parameterSource, getSumRowMapper());
        return queryResult.size() > 0 ? queryResult.get(0) : 0d;
    }

    @Override
    public List<ChargeDeliveryDTO> findAllChargesNotDelivered() {
        return jdbcTemplateWithNamedParameter.query("SELECT c.id as chargeId, c.delivered as delivered, " +
                "p.description as description, r.number as roomNumber FROM " + getTableName()
                + " c JOIN " + Product.TABLE_NAME + " p ON p.id = c.product_id JOIN " + Reservation.TABLE_NAME + " res ON res.id = c.reservation_id JOIN " +
                Room.TABLE_NAME + " r ON r.id = res.room_id WHERE "
                + Charge.KEY_DELIVERED + " = FALSE ", getRowMapperOfChargeDeliveryDTO());
    }

    @Override
    public int updateChargeToDelivered(long chargeId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("chargeId", chargeId);
        return jdbcTemplateWithNamedParameter
                .update("UPDATE " + getTableName() + " SET " +
                        Charge.KEY_DELIVERED + " = TRUE WHERE id = :chargeId", parameters);
    }

    private RowMapper<Double> getSumRowMapper() {
        return (resultSet, i) -> resultSet.getDouble(1);
    }

    private RowMapper<Charge> getRowMapperOfChargeRoomReservationDTO() {
        return ((resultSet, i) -> new Charge(resultSet));
    }

    private RowMapper<ChargeDeliveryDTO> getRowMapperOfChargeDeliveryDTO() {
        return ((resultSet, i) -> new ChargeDeliveryDTO(resultSet));
    }


}
*/