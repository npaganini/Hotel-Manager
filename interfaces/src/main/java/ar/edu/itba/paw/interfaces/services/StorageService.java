package ar.edu.itba.paw.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String saveProductImg(MultipartFile file);
}
