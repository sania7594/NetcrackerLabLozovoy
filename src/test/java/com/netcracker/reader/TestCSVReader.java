package com.netcracker.reader;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


public class TestCSVReader {
    @Test
    public void testReadLines() throws URISyntaxException {
        CSVReader reader = new CSVReader(Reader.getFileFromResource("tableCSV.csv"));
        String[] lines = reader.readLines();

        assertEquals("internet, 0,  123,    456,    0, name,    0,    male,   1234, 56789,  111", lines[0]);
    }



}
