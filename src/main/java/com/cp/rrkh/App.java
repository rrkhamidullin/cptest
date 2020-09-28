package com.cp.rrkh;

import com.cp.rrkh.readers.CSVReader;
import com.cp.rrkh.readers.JSONReader;
import com.cp.rrkh.readers.ReaderBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@Configuration
public class App {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(App.class, args)));
    }

    @Bean(name = "jsonReaderBuilder")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ReaderBuilder jsonReaderBuilder() {
        return fileName -> new JSONReader(fileName, Files.newBufferedReader(Paths.get(fileName)));
    }

    @Bean(name = "csvReaderBuilder")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ReaderBuilder csvReaderBuilder() {
        return fileName -> new CSVReader(fileName, Files.newBufferedReader(Paths.get(fileName)));
    }

}