package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.product.Product;

import java.io.File;

public interface ProductService {
    Product saveProduct(String name, double price, String filePath);

    boolean unableProduct(long productId);

    boolean enableProduct(long productId);
}
