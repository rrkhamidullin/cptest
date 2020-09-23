package com.cardpay.rrkh.parse;

import java.util.stream.Stream;

/**
 * Обработчик файла.
 */
public interface FileParser {

    String OK = "OK";
    String INVALID = "INVALID";

    Stream<Row> rows();

    void close() throws Exception;
}
