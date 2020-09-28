package com.cp.rrkh;

import com.cp.rrkh.readers.Reader;
import com.cp.rrkh.readers.ReaderProvider;
import com.cp.rrkh.readers.Row;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

/**
 * Асинхронный процессор файлов.
 */
@Slf4j
@Component
public class AsyncFilesProcessor {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private ReaderProvider readerProvider;

    /**
     * Получить читатель файла и вычитать его в STDOUT.
     *
     * @param fileName имя файла
     */
    @Async
    public CompletableFuture<Void> processFile(String fileName) {
        log.info("Processing: {}", fileName);
        Reader reader = null;
        try {
            reader = readerProvider.reader(fileName);
            JsonGenerator jsonGenerator = createGenerator();
            Iterator<Row> iterator = reader.rows().iterator();
            while (iterator.hasNext()) {
                jsonGenerator.writeObject(iterator.next());
                jsonGenerator.writeRaw('\n');
            }
        } catch (Exception e) {
            log.error("File {} parse error. E: ", fileName, e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return CompletableFuture.completedFuture(null);
    }

    private static JsonGenerator createGenerator() throws IOException {
        JsonGenerator jsonGenerator = JSON_FACTORY.createGenerator(System.out, JsonEncoding.UTF8);
        jsonGenerator.setCodec(OBJECT_MAPPER);
        return jsonGenerator;
    }
}