package ar.edu.itba.paw.webapp.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FilesUtils {

    // TODO
    public static String saveFile(final InputStream file, final String fileName) throws IOException {
        // FIX THIS, USE ANOTHER PATH TO SAVE FILES
        Path path = FileSystems.getDefault().getPath("target/" + fileName);

        File targetFile = new File(path.toUri());
        OutputStream outStream = new FileOutputStream(targetFile);

        byte[] bytes = new byte[(int) file.available()];
        DataInputStream dataInputStream = new DataInputStream(file);
        dataInputStream.readFully(bytes);

        outStream.write(bytes);

        return path.toString();
    }

    // TODO
    public static byte[] loadImg(final String imgPath) throws IOException {
        final File imgFile = new File(imgPath);
        return FileUtils.readFileToByteArray(imgFile);
    }
}
