package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    private final ServletContext servletContext;

    @Autowired
    public StorageServiceImpl(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String saveProductImg(MultipartFile multipartFile) {
        LOGGER.debug("About to save product's img inside server storage " + multipartFile.getOriginalFilename());
        String imgsPath = servletContext.getRealPath("resources/utilities/images");
        File file = new File(imgsPath + "/" + multipartFile.getOriginalFilename());
        try {
            file.mkdirs();
            file.createNewFile();
            multipartFile.transferTo(file);
            return file.getAbsolutePath();
        } catch (IOException e) {
            LOGGER.error("There was an error saving product's img inside server storage", e);
            e.printStackTrace();
        }
        return null;
    }
}
