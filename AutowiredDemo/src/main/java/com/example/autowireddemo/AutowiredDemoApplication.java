package com.example.autowireddemo;

import com.example.autowireddemo.core.annotation.EnableMyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMyAutoConfiguration
@SpringBootApplication
public class AutowiredDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AutowiredDemoApplication.class, args);
    }
    
}
