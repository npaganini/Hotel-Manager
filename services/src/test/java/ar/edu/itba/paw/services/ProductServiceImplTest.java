package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    private final Product product = new Product("Product", 2.01);
    private final Product productWithMaxIntegerValueId = new Product(Integer.MAX_VALUE, "Product", 3.45, null, true);

    @Before
    public void init() {
        Mockito.when(productDao.updateProductEnable(1L, true)).thenReturn(1);
        Mockito.when(productDao.updateProductEnable(1L, false)).thenReturn(1);
        Mockito.when(productDao.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productDao.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(productDao.findById(Integer.MAX_VALUE)).thenReturn(Optional.of(productWithMaxIntegerValueId));
    }

    @Test
    public void unableExistentProductTest() throws EntityNotFoundException {
        assertTrue("Product should be unabled", productService.disableProduct(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void unableNonExistentProductTest() throws EntityNotFoundException {
        productService.disableProduct(2L);
    }

    @Test
    public void enableExistentProductTest() throws EntityNotFoundException {
        assertTrue("Product should be enabled", productService.enableProduct(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void enableNonExistentProductTest() throws EntityNotFoundException {
        productService.enableProduct(2L);
    }

    @Test
    public void handleMaxValueIntegerAsId() throws EntityNotFoundException {
        Product product = productService.findProductById(Integer.MAX_VALUE);
        assertNotNull(product);
    }
}
