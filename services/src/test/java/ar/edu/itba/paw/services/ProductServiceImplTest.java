package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    private static final long ID_1 = 1L;
    private static final boolean FALSE = false;
    private static final boolean TRUE = true;
    private static final int BOOLEAN_INT_TRUE = 1;
    private static final String PRODUCT_NAME_1 = "Snickers";
    private static final double PRODUCT_PRICE_1 = 15.99;

    private static Product product1 = new Product(PRODUCT_NAME_1, PRODUCT_PRICE_1);

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    /**
     * @function_to_test boolean save(Product product)
     * uses productDao.save(Product)
     **/
    @Test
    public void testSave() {
        // 1. Setup!
        Mockito.when(productDao.save(product1)).thenReturn(product1);
        // 2. SUT
        Product productCreated = productService.saveProduct(product1);
        // 3. Asserts
        Assert.assertNotNull(productCreated);
        Assert.assertEquals(product1, productCreated);
    }

    /**
     * @function_to_test boolean unableProduct(long productId)
     * uses productDao.updateProductEnable(long productId)
     **/
    @Test
    public void testUnableProduct() throws Exception {
        // 1. Setup!
        Mockito.when(productDao.findById(Math.toIntExact(ID_1))).thenReturn(java.util.Optional.of(product1));
        Mockito.when(productDao.updateProductEnable(ID_1, FALSE)).thenReturn(BOOLEAN_INT_TRUE);
        // 2. SUT
        boolean productDisabled = productService.unableProduct(ID_1);
        // 3. Asserts
        Assert.assertTrue(productDisabled);
    }

    /**
     * @function_to_test boolean enableProduct(long productId)
     * uses productDao.updateProductEnable(long productID)
     **/
    @Test
    public void testEnableProduct() throws Exception {
    }
}
