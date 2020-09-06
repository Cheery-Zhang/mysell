package com.yes.mysell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MysellApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysellApplication.class, args);
    }

}
