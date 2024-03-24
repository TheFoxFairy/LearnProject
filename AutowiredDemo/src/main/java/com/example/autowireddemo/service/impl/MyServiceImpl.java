package com.example.autowireddemo.service.impl;

import com.example.autowireddemo.core.annotation.MyService;
import com.example.autowireddemo.service.TestService;

@MyService
public class MyServiceImpl implements TestService {
    
    @Override
    public String say(){
        return "Hello World!!!";
    }
}
