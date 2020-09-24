package com.cp.rrkh.parse.readers;

import java.io.IOException;

/**
 * Функция создания читателя/преобразователя файла.
 *
 * @param <V> тип читателя/преобразователя файла.
 */
public interface ReaderCreator<V extends FileReader> {
    V create(String fileName) throws IOException;
}
