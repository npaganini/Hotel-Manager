package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.product.Product;

import java.util.List;

public interface ProductDao extends SimpleDao<Product> {
    List<Product> getAllProducts();

    int updateProductEnable(long productId, boolean enable);

    List<Product> findAllProductsByDescription(String description);
}
