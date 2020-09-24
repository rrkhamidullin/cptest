package com.cp.rrkh;

import com.cp.rrkh.parse.readers.Readers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class FilesProcessor {

    @Autowired
    private AsyncRowsToStdoutWriter asyncRowsToStdoutWriter;

    public void processFiles(Collection<String> fileNames) {
        log.info("Got files: {}", fileNames);
        CompletableFuture.allOf(fileNames.stream().map(Readers::create).filter(Objects::nonNull)
                .map(asyncRowsToStdoutWriter::writeRowsFromReader).toArray(CompletableFuture[]::new)).join();
    }
}
