package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

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
    public PaginatedDTO<Product> findAllActive(int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Product> entityRoot = cqCount.from(Product.class);
        cqCount.select(builder.count(entityRoot));
        Path<Boolean> active = entityRoot.get("enable");
        cqCount.where(builder.and(builder.isTrue(active)));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Product> activeReservations = em.createQuery("SELECT p FROM Product AS p WHERE p.enable = true", Product.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(activeReservations, (count));
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
