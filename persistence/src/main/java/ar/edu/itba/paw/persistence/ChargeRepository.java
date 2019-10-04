package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.entities.ProductChargeDto;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ChargeRepository extends SimpleRepository<Charge> implements ChargeDao {

    private final static RowMapper<Charge> ROW_MAPPER = (resultSet, rowNum) -> new Charge(resultSet);

    @Autowired
    public ChargeRepository(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
        jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY, " +
                "product_id INTEGER REFERENCES product (id), " +
                "reservation_id INTEGER REFERENCES reservation (id))");
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
    public Map<Product, Integer> getAllChargesByUser(long userID) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("reservation_id", userID);
        return jdbcTemplateWithNamedParameter.query(
                "SELECT ans.description, ans.price, ans.count as amount "
                        + " FROM (( SELECT "
                        + getTableName() + ".product_id, count(" + getTableName() + ".product_id) "
                        + " FROM " + getTableName()
                        + " WHERE " + getTableName() + ".reservation_id = " + userID
                        + " GROUP BY " + getTableName() + ".product_id) as chargedProducts "
                        + " JOIN " + Product.TABLE_NAME + " ON "
                        + "(chargedProducts.product_id = " + Product.TABLE_NAME + ".id)) as ans "
                , rs -> {
                    Map<Product, Integer> ans = new HashMap<>();
                    while (rs.next()) {
                        Product p = new Product(rs.getString(1), rs.getFloat(2));
                        ans.put(p, rs.getInt(3));
                    }
                    return ans;
                });
    }

    private RowMapper<ProductChargeDto> getRowMapperWithJoin() {
        return ((resultSet, i) -> new ProductChargeDto(resultSet));
    }

 @Override
    public List<ChargeDTO> findChargeByReservationHash(long reservationId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("reservationId", reservationId);
        return jdbcTemplateWithNamedParameter
                .query("SELECT * FROM " + Charge.TABLE_NAME + " NATURAL JOIN " +
                        Product.TABLE_NAME + " NATURAL JOIN " + Reservation.TABLE_NAME +
                        " r WHERE r.id = :reservationId", parameters, getRowMapperOfChargeDTO());
    }

    private RowMapper<ChargeDTO> getRowMapperOfChargeDTO() {
        return ((resultSet, i) -> new ChargeDTO(resultSet));
    }
}
