package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    boolean disableProduct(long productId) throws Exception;

    boolean enableProduct(long productId) throws Exception;

    PaginatedDTO<Product> getAll(int page, int pageSize);

    Product findProductById(long productId) throws EntityNotFoundException;
}
