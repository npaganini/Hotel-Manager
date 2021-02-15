package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.dtos.ProductResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;

public interface ProductService {
    Product saveProduct(Product product);

    boolean disableProduct(long productId) throws Exception;

    boolean enableProduct(long productId) throws Exception;

    PaginatedDTO<ProductResponse> getAll(int page, int pageSize);

    Product findProductById(long productId) throws EntityNotFoundException;
}
