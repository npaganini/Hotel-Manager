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
public class ProductRepositoryHibernate implements ProductDao {
    @PersistenceContext
    private EntityManager em;

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
    public Product save(Product product) {
        final Product productToAdd = new Product(product.getDescription(), product.getPrice(), product.getFile());
        em.persist(productToAdd);
        return productToAdd;
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }
}
