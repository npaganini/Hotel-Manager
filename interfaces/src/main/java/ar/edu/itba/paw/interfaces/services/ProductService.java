package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    boolean unableProduct(long productId) throws Exception;

    boolean enableProduct(long productId) throws Exception;

    List<Product> getAll();

    Product findProductById(long productId) throws EntityNotFoundException;

}
