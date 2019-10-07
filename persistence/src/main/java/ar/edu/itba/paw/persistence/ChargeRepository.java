package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChargeRepository extends SimpleRepository<Charge> implements ChargeDao {

    private final static RowMapper<Charge> ROW_MAPPER = (resultSet, rowNum) -> new Charge(resultSet);

    @Autowired
    public ChargeRepository(DataSource dataSource) {
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
    public boolean addCharge(Charge product) {
        try {
            this.save(product);
        } catch (Exception sqlE) {
            return false;
        }
        return true;
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
    public List<ChargeRoomReservationDTO> findChargeByReservationHash(long reservationId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("reservationId", reservationId);
        return jdbcTemplateWithNamedParameter
                .query("SELECT * FROM " + Charge.TABLE_NAME + " NATURAL JOIN " +
                        Product.TABLE_NAME + " NATURAL JOIN " + Reservation.TABLE_NAME +
                        " r WHERE r.id = :reservationId", parameters, getRowMapperOfChargeRoomReservationDTO());
    }

    @Override
    public double sumCharge(long reservationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("reservationId", reservationId);
        return jdbcTemplateWithNamedParameter.query("SELECT sum(p.price) FROM charge c JOIN product p " +
                        "ON p.id = c.product_id JOIN reservation r ON c.reservation_id = r.id WHERE r.id = :reservationId GROUP BY r.id",
                parameterSource, getSumRowMapper()).get(0);
    }

    @Override
    public List<ChargeRoomReservationDTO> findAllChargesNotDelivered() {
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName()
                + " JOIN " + Reservation.TABLE_NAME + " res ON res.id = reservation_id JOIN " + Room.TABLE_NAME +
                " r ON res.room_id = r.id WHERE "
                + Charge.KEY_DELIVERED + " = FALSE ", getRowMapperOfChargeRoomReservationDTO());
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

    private RowMapper<ChargeRoomReservationDTO> getRowMapperOfChargeRoomReservationDTO() {
        return ((resultSet, i) -> new ChargeRoomReservationDTO(resultSet));
    }


}
