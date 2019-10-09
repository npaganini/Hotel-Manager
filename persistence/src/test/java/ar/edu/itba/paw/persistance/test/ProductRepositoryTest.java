package ar.edu.itba.paw.persistance.test;

import ar.edu.itba.paw.persistance.config.TestConfig;
import ar.edu.itba.paw.persistence.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ProductRepositoryTest {
    @Autowired
    private DataSource ds;

    @Autowired
    private ProductRepository productRepository;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    /**
     * @function_to_test List<Product> getAllProducts()
     */
    @Test
    public void testGetAllProducts() {}

    /**
     * @function_to_test List<Product> getAllProductsForTable()
     */
    @Test
    public void testGetAllProductsForTable() {}

    /**
     * @function_to_test int updateProductEnable(long productId, boolean enable)
     */
    @Test
    public void testUpdateProductEnable() {}

    /**
     * @function_to_test List<Product> findAllProductsByDescription(String description)
     */
    @Test
    public void testFindAllProductsByDescription() {}
}
