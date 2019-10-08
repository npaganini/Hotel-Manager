package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.misc.Request;

import java.io.File;
import java.util.List;

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
    public boolean unableProduct(long productId) throws RequestInvalidException {
        LOGGER.debug("About to unable product for visibility with id " + productId);
        if (!productDao.findById(productId).orElseThrow(RequestInvalidException::new).isEnable()) {
            throw new RequestInvalidException();
        }
        return productDao.updateProductEnable(productId, false) > 0;
    }

    @Override
    public boolean enableProduct(long productId) throws RequestInvalidException {
        LOGGER.debug("About to enable product for visibility with id " + productId);
        if (productDao.findById(productId).orElseThrow(RequestInvalidException::new).isEnable()) {
            throw new RequestInvalidException();
        }
        return productDao.updateProductEnable(productId, true) > 0;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsForTable(){return productDao.getAllProductsForTable();}

    @Override
    public Product findProductById(long productId) throws EntityNotFoundException {
        return productDao.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Can't find product with id " + productId));
    }

}
