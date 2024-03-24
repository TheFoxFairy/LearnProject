package com.example.interfaceunifiedcalldemo.service.impl;

import com.example.interfaceunifiedcalldemo.enums.RequestEnum;
import com.example.interfaceunifiedcalldemo.factory.DemoServiceFactory;
import com.example.interfaceunifiedcalldemo.service.ConsumerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Resource
    private DemoServiceFactory demoServiceFactory;
    
    @Override
    public String get(String name) {
        RequestEnum requestEnum = RequestEnum.getByName(name);
        if(Objects.isNull(requestEnum)) {
            return "不存在";
        }
        return demoServiceFactory.getInstance(requestEnum);
    }
    
}
