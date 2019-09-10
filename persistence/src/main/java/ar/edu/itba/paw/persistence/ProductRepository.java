package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ProductRepository extends SimpleRepository<Product> implements ProductDao {

    private final static RowMapper<Product> ROW_MAPPER = (resultSet, i) -> new Product(resultSet);

    @Autowired
    public ProductRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id SERIAL PRIMARY KEY," +
                "description VARCHAR(150)," +
                "price DOUBLE PRECISION)");
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
}
