package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;

public interface ProductDao extends SimpleDao<Product> {
    int updateProductEnable(long productId, boolean enable);

    PaginatedDTO<Product> findAllActive(int page, int pageSize);
}
