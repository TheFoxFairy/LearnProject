package com.example.interfaceunifiedcalldemo.service.impl;

import com.example.interfaceunifiedcalldemo.service.DemoService3;
import org.springframework.stereotype.Service;

@Service
public class DemoService3Impl implements DemoService3 {
    @Override
    public String say() {
        System.out.println(this.getClass().getName());
        return this.getClass().getName();
    }
}
