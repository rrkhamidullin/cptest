package com.cardpay.rrkh;

import com.cardpay.rrkh.parse.Parsers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

@SpringBootApplication
@Configuration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            RowsProcessor rowsProcessor = ctx.getBean(RowsProcessor.class);
            Arrays.stream(args).map(Parsers::create).filter(Objects::nonNull).forEach(rowsProcessor::process);
        };
    }

    @Bean
    public RowsProcessor rowsProcessor() {
        return new RowsProcessor();
    }
}