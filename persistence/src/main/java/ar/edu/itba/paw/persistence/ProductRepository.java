package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductRepository extends SimpleRepository<Product> implements ProductDao {

    private final static RowMapper<Product> ROW_MAPPER = (resultSet, i) -> new Product(resultSet);

    @Autowired
    public ProductRepository(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
        if (jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .query("SELECT * FROM " + Product.TABLE_NAME, getRowMapper()).size() == 0)
            getHardcodedProducts().parallelStream().forEach(this::save);
    }

    @Override
    RowMapper<Product> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    String getTableName() {
        return Product.TABLE_NAME;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName(), getRowMapper());
    }

    @Override
    public int updateProductEnable(long productId, boolean enable) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("productId", productId);
        parameters.addValue("enable", enable);
        return jdbcTemplateWithNamedParameter.update("UPDATE " + Product.TABLE_NAME + " SET " +
                Product.KEY_ENABLE + "= :enable WHERE " + Product.KEY_ID + "=:productId", parameters);
    }
}
