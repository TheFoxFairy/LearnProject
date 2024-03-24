package com.example.autowireddemo.controller;

import com.example.autowireddemo.core.annotation.MyAutowired;
import com.example.autowireddemo.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TestController {
    @MyAutowired
    private TestService testService;
    
    @GetMapping("/test")
    public String get(){
        return testService.say();
    }
}
