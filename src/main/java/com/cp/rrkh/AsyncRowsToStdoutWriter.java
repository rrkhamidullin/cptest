package com.cp.rrkh;

import com.cp.rrkh.readers.Reader;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Асинхронный писатель строк файлов в STDOUT
 */
@Slf4j
@Component
public class AsyncRowsToStdoutWriter {

    private final Gson gson = new Gson();

    /**
     * Вычитать поток из reader и закрыть его.
     *
     * @param reader читатель файла
     */
    @Async
    public CompletableFuture<Void> writeRowsFromReader(Reader reader) {
        try {
            reader.rows().map(gson::toJson).forEach(System.out::println);
        } catch (Exception e) {
            log.error("File {} parse error. E: {}", reader.getFileName(), e);
        } finally {
            reader.close();
        }
        return CompletableFuture.completedFuture(null);
    }
}
