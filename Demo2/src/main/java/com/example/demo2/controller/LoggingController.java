package com.example.demo2.controller;

import com.example.demo2.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
    @Autowired
    private LoggingService loggingService;

    @GetMapping("/")
    public void test(){
        loggingService.logging();
    }
}
