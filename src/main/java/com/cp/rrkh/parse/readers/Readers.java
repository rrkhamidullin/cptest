package com.cp.rrkh.parse.readers;

import com.cp.rrkh.parse.FormatNotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Читатели файлов различных форматов.
 */
@Slf4j
public enum Readers {
    CSV(Collections.singleton("csv"), CSVFileReader::new),
    JSON(Collections.singleton("json"), JSONFileReader::new),
    XLS(Arrays.asList("xls", "xlsx"), s -> {
        throw new NotImplementedException();
    });

    private final Collection<String> extensions;
    private final ReaderCreator<? extends FileReader> readerCreator;

    Readers(Collection<String> extensions, ReaderCreator<? extends FileReader> readerCreator) {
        this.extensions = extensions;
        this.readerCreator = readerCreator;
    }

    /**
     * Создать читателя на основе имени файла
     *
     * @param fileName имя файла
     * @return в случае неуспеха вернёт NULL
     */
    public static FileReader create(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            log.error("File reader creation failed. File name is empty.");
            return null;
        }
        String[] split = fileName.split("\\.");
        if (split.length < 2) {
            log.error("File reader creation failed. File name have wrong format.");
            return null;
        }
        log.info("Creating file reader for: {}", fileName);
        try {
            return byExtension(split[split.length - 1]).readerCreator.create(fileName);
        } catch (Exception e) {
            log.error("File reader creation failed for file name: {}. Error: {}", fileName, e);
            return null;
        }
    }

    private static Readers byExtension(String extension) {
        return Arrays.stream(Readers.values())
                .filter(readers -> readers.extensions.contains(extension)).findAny()
                .orElseThrow(() -> new FormatNotSupportedException(extension));
    }
}
