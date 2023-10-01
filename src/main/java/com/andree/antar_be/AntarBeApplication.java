package com.andree.antar_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AntarBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntarBeApplication.class, args);
    }
}
