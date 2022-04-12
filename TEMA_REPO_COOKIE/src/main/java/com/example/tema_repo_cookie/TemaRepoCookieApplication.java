package com.example.tema_repo_cookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TemaRepoCookieApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemaRepoCookieApplication.class, args);
    }
}
