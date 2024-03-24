package com.example.interfaceunifiedcalldemo.service.impl;

import com.example.interfaceunifiedcalldemo.service.DemoService1;
import org.springframework.stereotype.Service;

@Service
public class DemoService1Impl implements DemoService1 {
    @Override
    public String say() {
        System.out.println(this.getClass().getName());
        return this.getClass().getName();
    }
}
