package com.cp.rrkh.readers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Предоставляет читателей файлов по именам файлов.
 */
@Slf4j
@Component
public class ReaderProvider {

    private static final String READER_BUILDER_NAME_POSTFIX = "ReaderBuilder";

    @Autowired
    private ApplicationContext appContext;

    /**
     * Получить соответствующую разрешению реализацию читателя файла.
     *
     * @param fileName имя файла
     * @throws IOException
     */
    public Reader reader(String fileName) throws IOException {
        String beanName = getReaderBuilderName(fileName);
        ReaderBuilder readerBuilder = appContext.getBean(beanName, ReaderBuilder.class);
        return readerBuilder.build(fileName);
    }

    private static String getReaderBuilderName(String fileName) {
        return getExtension(fileName) + READER_BUILDER_NAME_POSTFIX;
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
