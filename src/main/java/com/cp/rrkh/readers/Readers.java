package com.cp.rrkh.readers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;

/**
 * Читатели файлов различных форматов.
 */
@Slf4j
public enum Readers {
    CSV(Collections.singleton("csv"), CSVReader::new),
    JSON(Collections.singleton("json"), JSONReader::new),
    XLS(Arrays.asList("xls", "xlsx"), (fileName, br) -> {
        throw new FormatNotSupportedException(fileName);
    });

    private final Collection<String> extensions;
    private final BiFunction<String, BufferedReader, ? extends Reader> readerCreator;

    Readers(Collection<String> extensions, BiFunction<String, BufferedReader, ? extends Reader> readerCreator) {
        this.extensions = extensions;
        this.readerCreator = readerCreator;
    }

    /**
     * Создать читателя на основе имени файла
     *
     * @param fileName имя файла
     * @return в случае неуспеха вернёт NULL
     */
    public static Reader create(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            log.error("File reader creation failed. File name is empty.");
            return null;
        }
        try {
            String extension = getExtension(fileName);
            BufferedReader br = createBufferedReader(fileName);
            return byExtension(extension).readerCreator.apply(fileName, br);
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

    private static BufferedReader createBufferedReader(String fileName) throws IOException {
        log.info("Creating file reader for: {}", fileName);
        return Files.newBufferedReader(Paths.get(fileName));
    }

    private static String getExtension(String fileName) {
        String[] split = fileName.split("\\.");
        if (split.length < 2) {
            log.error("File reader creation failed. File name have wrong format.");
            throw new FormatNotSupportedException(fileName);
        }
        return split[split.length - 1];
    }
}
