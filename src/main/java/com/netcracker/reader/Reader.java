package com.netcracker.reader;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/*
@author Lozovoy
@version 1.0
class reader

 */

public interface Reader {
    static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Reader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException("File not found " + fileName);
        else
            return new File(resource.toURI());
    }

    String[] readLines();
}
