/*package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

//@Repository
public class ProductRepositoryJDBC extends SimpleRepositoryJDBC<Product> implements ProductDao {

    private final static RowMapper<Product> ROW_MAPPER = (resultSet, i) -> new Product(resultSet);

//    @Autowired
    public ProductRepositoryJDBC(DataSource dataSource) {
        super(new NamedParameterJdbcTemplate(dataSource));
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
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " +
                getTableName() + " WHERE enable = TRUE", getRowMapper());
    }

    @Override
    public List<Product> getAllProductsForTable() {
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM " + getTableName() , getRowMapper());
    }

    @Override
    public int updateProductEnable(long productId, boolean enable) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("productId", productId);
        parameters.addValue("enable", enable);
        return jdbcTemplateWithNamedParameter.update("UPDATE " + Product.TABLE_NAME + " SET " +
                Product.KEY_ENABLE + "= :enable WHERE " + Product.KEY_ID + "=:productId", parameters);
    }

    @Override
    public List<Product> findAllProductsByDescription(String description) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("description", description);
        return jdbcTemplateWithNamedParameter.query("SELECT * FROM product WHERE description=:description", parameters, getRowMapper());
    }
}
*/