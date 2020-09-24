package com.cp.rrkh.readers;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Общий функционал присущий всем читателям файлов в независимости от формата.
 */
public abstract class AbstractFileReader implements FileReader {

    private final String fileName;
    private final BufferedReader br;
    private long counter = 0L;

    public AbstractFileReader(String fileName) throws IOException {
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

    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * Получить строку в виде массива полей.
     * @param line строка
     * @return массив полей или массив нулевого размера в случае неуспеха
     */
    protected abstract String[] mapRow(String line);

    private Row mapRow(String[] columns) {
        if (columns.length != 4) {
            return new Row()
                    .setLine(++counter)
                    .setResult(CORRUPT_LINE);
        }
        Long id = parseLong(columns[0]);
        BigDecimal amount = parseDecimal(columns[1]);
        String currency = columns[2];
        String result = checkResult(id, amount, currency) ? OK : getWrongResult(id, amount, currency);
        return new Row()
                .setId(id)
                .setAmount(amount)
                .setCurrency(currency)
                .setComment(columns[3])
                .setFilename(fileName)
                .setLine(++counter)
                .setResult(result);
    }

    private Long parseLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal parseDecimal(String number) {
        try {
            return new BigDecimal(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean checkResult(Long id, BigDecimal amount, String currency) {
        return id != null && amount != null && !StringUtils.isEmpty(currency);
    }

    private String getWrongResult(Long id, BigDecimal amount, String currency) {
        Collection<String> corruptFields = new LinkedList<>();
        if (id == null) {
            corruptFields.add(ID);
        }
        if (amount == null) {
            corruptFields.add(AMOUNT);
        }
        if (StringUtils.isEmpty(currency)) {
            corruptFields.add(CURRENCY);
        }
        return "Corrupt fields: " + String.join(", ", corruptFields);
    }
}
