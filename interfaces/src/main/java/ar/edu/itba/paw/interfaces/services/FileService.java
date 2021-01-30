package ar.edu.itba.paw.interfaces.services;

import java.io.InputStream;

public interface FileService {
    public void saveFile(final InputStream file, final String fileName);
    public byte[] loadImg(final String imgPath);
}
