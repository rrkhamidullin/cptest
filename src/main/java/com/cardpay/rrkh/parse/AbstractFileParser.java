package com.cardpay.rrkh.parse;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class AbstractFileParser implements FileParser {

    private final String fileName;
    private final BufferedReader br;
    private long counter = 0L;

    public AbstractFileParser(String fileName) throws Exception {
        this.fileName = fileName;
        this.br = Files.newBufferedReader(Paths.get(fileName));
    }

    @Override
    public Stream<Row> rows() {
        return br.lines().map(this::mapRow).map(this::mapRow);
    }

    @Override
    public void close() throws Exception {
        br.close();
    }

    protected abstract String[] mapRow(String line);

    private Row mapRow(String[] columns) {
        if (columns.length != 4) {
            return new Row().setResult(INVALID);
        }
        Long id = parseLong(columns[0]);
        Long amount = parseLong(columns[1]);
        String currency = columns[2];
        return new Row()
                .setId(id)
                .setAmount(amount)
                .setCurrency(currency)
                .setComment(columns[3])
                .setFilename(fileName)
                .setLine(++counter)
                .setResult(id == null || amount == null || StringUtils.isEmpty(currency) ? INVALID : OK);
    }

    private Long parseLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            //log
            return null;
        }
    }
}
