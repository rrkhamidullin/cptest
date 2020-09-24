package com.cp.rrkh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class CmdRunner implements CommandLineRunner {

    @Autowired
    private FilesProcessor filesProcessor;

    @Override
    public void run(String... args) {
        filesProcessor.processFiles(Arrays.asList(args));
    }
}
