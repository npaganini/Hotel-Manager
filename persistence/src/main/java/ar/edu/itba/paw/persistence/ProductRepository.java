package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ProductRepository extends SimpleRepository<Product> implements ProductDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    private final static RowMapper<Product> ROW_MAPPER = (resultSet, i) -> new Product(resultSet);

    @Autowired
    public ProductRepository(DataSource dataSource, SimpleJdbcInsert simpleJdbcInsert) {
        super(new JdbcTemplate(dataSource));
        this.simpleJdbcInsert = simpleJdbcInsert;
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
