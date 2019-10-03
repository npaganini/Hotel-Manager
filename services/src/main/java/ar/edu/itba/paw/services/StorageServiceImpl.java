package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.StorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class StorageServiceImpl implements StorageService {

    //TODO do this dynamically
    private static final String imgsPath = "/home/tomas/workspace/paw-tpe/webapp/src/main/webapp/WEB-INF/imgs";

    @Override
    public String store(MultipartFile multipartFile) {
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
