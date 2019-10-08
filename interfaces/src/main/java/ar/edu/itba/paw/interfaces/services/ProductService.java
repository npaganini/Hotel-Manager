package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.product.Product;

import java.io.File;
import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    boolean unableProduct(long productId) throws Exception;

    boolean enableProduct(long productId) throws Exception;

    List<Product> getAll();

    List<Product> getAllProductsForTable();

    Product findProductById(long productId) throws EntityNotFoundException;

}
