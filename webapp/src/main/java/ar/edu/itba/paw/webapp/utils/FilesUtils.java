package ar.edu.itba.paw.webapp.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FilesUtils {

    // TODO
    public static String saveFile(final InputStream file, final String fileName) throws IOException {
        // FIX THIS, USE ANOTHER PATH TO SAVE FILES
        Path path = FileSystems.getDefault().getPath(System.getProperty("user.dir") + "/webapp/resources/" + fileName);

        byte[] buffer = new byte[file.available()];
        file.read(buffer);
        File targetFile = new File(path.toUri());
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);

        return path.toString();
    }

    // TODO
    public static byte[] loadImg(final String imgPath) {
        throw new NotImplementedException();
    }
}
