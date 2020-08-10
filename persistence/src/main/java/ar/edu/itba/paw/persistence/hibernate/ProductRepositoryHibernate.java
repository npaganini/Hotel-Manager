package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class ProductRepositoryHibernate extends SimpleRepositoryHibernate<Product> implements ProductDao {

    @Override
    public int updateProductEnable(long productId, boolean enable) {
        final TypedQuery<Product> query = em.createQuery("SELECT p FROM Product AS p WHERE p.id = :productId", Product.class);
        query.setParameter("productId", productId);
        Product product = query.getSingleResult();
        if (product != null) {
            product.setEnable(enable);
            em.merge(product);
            return 1;
        }
        return 0;
    }

    @Override
    String getModelName() {
        return Product.NAME + " ";
    }

    @Override
    Class<Product> getModelClass() {
        return Product.class;
    }
}
