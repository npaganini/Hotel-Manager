package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;

import java.io.File;

// TODO
public class ProductServiceImpl implements ProductService {

    @Override
    public Product saveProduct(String name, double price, String filePath) {
        return null;
    }

    @Override
    public String addImgForProduct(File file) {
        return null;
    }

    @Override
    public boolean unableProduct(long productId) {
        return false;
    }

    @Override
    public boolean enableProduct(long productId) {
        return false;
    }

}
