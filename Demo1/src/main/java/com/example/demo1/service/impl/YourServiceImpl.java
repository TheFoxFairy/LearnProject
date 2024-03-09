package com.example.demo1.service.impl;

import com.example.demo1.annotation.MyService;
import com.example.demo1.service.YourService;

@MyService
public class YourServiceImpl implements YourService {
    @Override
    public String say(){
        return "Hello Autowired!";
    }
}
