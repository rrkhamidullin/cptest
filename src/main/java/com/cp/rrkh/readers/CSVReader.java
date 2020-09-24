package com.cp.rrkh.readers;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;

public class CSVReader extends AbstractReader {

    private static final String CSV_DELIMITER = ",";

    public CSVReader(String fileName, BufferedReader br) {
        super(fileName, br);
    }

    @Override
    protected String[] mapLine(String line) {
        if (StringUtils.isEmpty(line)) {
            return new String[0];
        }
        return line.split(CSV_DELIMITER);
    }
}
