package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;

import java.io.File;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product saveProduct(String description, double price, String filePath) {
        return productDao.save(new Product(description, price, filePath));
    }

    @Override
    public boolean unableProduct(long productId) {
        return productDao.updateProductEnable(productId, false) > 0;
    }

    @Override
    public boolean enableProduct(long productId) {
        return productDao.updateProductEnable(productId, true) > 0;
    }

}
