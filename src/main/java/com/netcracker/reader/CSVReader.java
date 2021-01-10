package com.netcracker.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
@author Lozovoy
@version 1.0
class csv reader

 */

public class CSVReader implements Reader {
    private final File file;

    public CSVReader(File file) {
        this.file = file;
    }

    /**
     * @return  lines trim
     */
    @Override
    public String[] readLines() {
        String[] lines = new String[10];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (i == lines.length)
                    lines = expand(lines);

                lines[i++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] linesTrim = new String[i];
        System.arraycopy(lines, 0, linesTrim, 0, i);
        return linesTrim;
    }

    /**
     * @param data data
     * @return result data
     */
    private String[] expand(String[] data) {
        String[] tmp = data.clone();
        int size = tmp.length;
        data = new String[tmp.length << 1];
        System.arraycopy(tmp, 0, data, 0, size);

        return data;
    }
}
