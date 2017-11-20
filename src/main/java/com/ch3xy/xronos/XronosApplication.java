package com.ch3xy.xronos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ch3xy.xronos")
@EnableAutoConfiguration
@SpringBootApplication
public class XronosApplication {
    public static void main(String[] args) {
        SpringApplication.run(XronosApplication.class, args);
    }
}
