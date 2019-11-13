package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        LOGGER.debug("About to save product with description " + product.getDescription() + " and price " + product.getPrice());
        return productDao.save(product);
    }

    @Transactional
    @Override
    public boolean unableProduct(long productId) throws RequestInvalidException {
        LOGGER.debug("About to unable product for visibility with id " + productId);
        productDao.findById(Math.toIntExact(productId)).orElseThrow(RequestInvalidException::new);
        return productDao.updateProductEnable(productId, false) > 0;
    }

    @Transactional
    @Override
    public boolean enableProduct(long productId) throws RequestInvalidException {
        LOGGER.debug("About to enable product for visibility with id " + productId);
        productDao.findById(Math.toIntExact(productId)).orElseThrow(RequestInvalidException::new);
        return productDao.updateProductEnable(productId, true) > 0;
    }

    //TODO estos metodos estan repetidos
    @Transactional
    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    //TODO estos metodos estan repetidos
    @Transactional
    @Override
    public List<Product> getAllProductsForTable() {
        return productDao.findAll();
    }

    @Transactional
    @Override
    public Product findProductById(long productId) throws EntityNotFoundException {
        return productDao.findById(Math.toIntExact(productId)).orElseThrow(() ->
                new EntityNotFoundException("Can't find product with id " + productId));
    }

}
