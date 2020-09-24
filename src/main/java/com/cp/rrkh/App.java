package com.cp.rrkh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class App {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(App.class, args)));
    }
}