package com.cp.rrkh.readers;

import java.io.IOException;

/**
 * Построитель читателей файлов.
 * Для каждого файла создаётся новый.
 */
public interface ReaderBuilder {

    /**
     * Создать новый читатель файла
     *
     * @param fileName имя файла
     * @return подходящая реализация читателя файла
     * @throws IOException
     */
    Reader build(String fileName) throws IOException;
}
