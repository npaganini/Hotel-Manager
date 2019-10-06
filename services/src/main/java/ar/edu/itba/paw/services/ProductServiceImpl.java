package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product saveProduct(Product product) {
        LOGGER.debug("About to save product with description " + product.getDescription() + " and price " + product.getPrice());
        return productDao.save(product);
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
