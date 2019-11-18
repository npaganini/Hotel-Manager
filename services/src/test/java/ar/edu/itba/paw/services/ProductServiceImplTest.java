package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product = new Product("Product", 2d);

    @Before
    public void init() {
        Mockito.when(productDao.updateProductEnable(1L, true)).thenReturn(1);
        Mockito.when(productDao.updateProductEnable(1L, false)).thenReturn(1);
        Mockito.when(productDao.findById(1L)).thenReturn(Optional.ofNullable(product));
        Mockito.when(productDao.findById(2L)).thenReturn(Optional.empty());
    }

    @Test
    public void unableExistentProductTest() throws EntityNotFoundException {
        assertTrue("Product should be unabled", productService.unableProduct(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void unableNonExistentProductTest() throws EntityNotFoundException {
        productService.unableProduct(2L);
    }

    @Test
    public void enableExistentProductTest() throws EntityNotFoundException {
        assertTrue("Product should be enabled", productService.enableProduct(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void enableNonExistentProducTest() throws EntityNotFoundException {
        productService.enableProduct(2L);
    }



}
