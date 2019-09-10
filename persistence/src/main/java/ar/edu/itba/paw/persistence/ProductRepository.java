package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ProductRepository extends SimpleRepository<Product> implements ProductDao {

    @Autowired
    public ProductRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
    }

    @Override
    RowMapper<Product> getRawMapper() {
        return null;
    }

    @Override
    String getTableName() {
        return null;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return null;
    }
}
