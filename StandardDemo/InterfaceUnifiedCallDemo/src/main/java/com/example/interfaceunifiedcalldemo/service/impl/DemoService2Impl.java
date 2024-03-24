package com.example.interfaceunifiedcalldemo.service.impl;

import com.example.interfaceunifiedcalldemo.service.DemoService2;
import org.springframework.stereotype.Service;

@Service
public class DemoService2Impl implements DemoService2 {
    @Override
    public String say() {
        System.out.println(this.getClass().getName());
        return this.getClass().getName();
    }
}
