package com.cp.rrkh.readers;

import java.util.stream.Stream;

/**
 * Потоковый обработчик/читатель файла.
 * Возвращает содержимое файла построчно в виде POJO.
 */
public interface FileReader {

    String OK = "OK";
    String ID = "id";
    String AMOUNT = "amount";
    String CURRENCY = "currency";
    String CORRUPT_LINE = "corrupt row";

    /**
     * Получить поток обработанных строк.
     */
    Stream<Row> rows();

    /**
     * Закрыть файл. Нужно это обязательно сделать после того как файл вычитан.
     */
    void close() throws Exception;

    /**
     * Получить имя обрабатываемого файла.
     */
    String getFileName();
}
