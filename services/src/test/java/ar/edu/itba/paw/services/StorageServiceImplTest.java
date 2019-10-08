package ar.edu.itba.paw.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceImplTest {
    private static final String FILE_PATH_PRODUCT_IMGS = "resources/productImgs";

    @Mock
    private ServletContext servletContext;
    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private StorageServiceImpl storageService;

    /**
     * @function_to_test String saveProductImg(MultipartFile multipartFile)
     * uses servletContext.getRealPath(filePath)
     **/
    @Test
    public void testSaveProductImg() {
        // 1. Setup!
        Mockito.when(multipartFile.getOriginalFilename()).thenReturn(anyString());
        Mockito.when(servletContext.getRealPath(FILE_PATH_PRODUCT_IMGS)).thenReturn(FILE_PATH_PRODUCT_IMGS);
        // 2. SUT
        String imgFilePath = storageService.saveProductImg(multipartFile);
        // 3. Asserts
        Assert.assertNotNull(imgFilePath);
    }
}
