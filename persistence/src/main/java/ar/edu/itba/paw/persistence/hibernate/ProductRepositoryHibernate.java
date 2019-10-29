package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryHibernate extends SimpleRepositoryHibernate<Product> implements ProductDao {

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public int updateProductEnable(long productId, boolean enable) {
        return 0;
    }

    @Override
    public List<Product> findAllProductsByDescription(String description) {
        return null;
    }

    @Override
    public List<Product> getAllProductsForTable() {
        return null;
    }

    @Override
    String getTableName() {
        return Product.TABLE_NAME;
    }

    @Override
    Class<Product> getModelClass() {
        return Product.class;
    }
}
