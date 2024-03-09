package com.example.demo1.controller;

import com.example.demo1.annotation.MyAutowired;
import com.example.demo1.service.YourService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YourController {
    @MyAutowired
    private YourService yourService;

    @GetMapping("/")
    public String say(){
        return yourService.say();
    }
}
