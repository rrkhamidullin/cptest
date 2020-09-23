package com.cardpay.rrkh.parse;

import org.springframework.util.StringUtils;

public class CSVFileParser extends AbstractFileParser {

    private static final String CSV_DELIMITER = ",";

    public CSVFileParser(String fileName) throws Exception {
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
