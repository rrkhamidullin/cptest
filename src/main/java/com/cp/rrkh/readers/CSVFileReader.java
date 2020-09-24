package com.cp.rrkh.readers;

import org.springframework.util.StringUtils;

import java.io.IOException;

public class CSVFileReader extends AbstractFileReader {

    private static final String CSV_DELIMITER = ",";

    public CSVFileReader(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    protected String[] mapRow(String line) {
        if (StringUtils.isEmpty(line)) {
            return new String[0];
        }
        return line.split(CSV_DELIMITER);
    }
}
