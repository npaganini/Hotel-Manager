package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Component
public class StorageServiceImpl implements StorageService {

    private final ServletContext servletContext;

    @Autowired
    public StorageServiceImpl(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String saveProductImg(MultipartFile multipartFile) {
        String imgsPath = servletContext.getRealPath("resources/productImgs");
        File file = new File(imgsPath + "/" + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
