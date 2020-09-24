package com.cp.rrkh;

import com.cp.rrkh.readers.FileReader;
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

    @Async
    public CompletableFuture<Void> writeRowsFromReader(FileReader fileReader) {
        try {
            fileReader.rows().map(gson::toJson).forEach(System.out::println);
        } catch (Throwable e) {
            log.error("File {} parse error. E: {}", fileReader.getFileName(), e);
        } finally {
            try {
                fileReader.close();
            } catch (Exception e) {
                log.error("Error.", e);
            }
        }
        return CompletableFuture.completedFuture(null);
    }
}
