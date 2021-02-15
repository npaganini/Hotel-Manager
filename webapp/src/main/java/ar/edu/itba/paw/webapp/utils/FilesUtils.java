package ar.edu.itba.paw.webapp.utils;

import org.apache.commons.io.FileUtils;
import sun.misc.IOUtils;

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
        outStream.write(IOUtils.readAllBytes(file));

        return path.toString();
    }

    // TODO
    public static byte[] loadImg(final String imgPath) throws IOException {
        final File imgFile = new File(imgPath);
        return FileUtils.readFileToByteArray(imgFile);
    }
}
