package com.cp.rrkh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class CmdRunner implements CommandLineRunner {

    @Autowired
    private AsyncFilesProcessor asyncFilesProcessor;

    @Override
    public void run(String... args) {
        List<String> fileNames = Arrays.asList(args);
        log.info("Got files: {}", fileNames);
        CompletableFuture.allOf(
                fileNames.stream()
                        .map(asyncFilesProcessor::processFile)
                        .toArray(CompletableFuture[]::new)
        ).join();
    }
}
