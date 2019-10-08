package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.product.Product;

import java.io.File;
import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    boolean unableProduct(long productId);

    boolean enableProduct(long productId);

    List<Product> getAll();

    Product findProductById(long productId);
}
