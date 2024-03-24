package com.example.interfaceunifiedcalldemo.controller;

import com.example.interfaceunifiedcalldemo.service.ConsumerService;
import com.example.interfaceunifiedcalldemo.service.DemoService1;
import com.example.interfaceunifiedcalldemo.service.DemoService2;
import com.example.interfaceunifiedcalldemo.service.DemoService3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
@Slf4j
public class TestController {
    
    @Resource
    ConsumerService consumerService;
    
    @GetMapping("/get")
    public String get(String name){
        log.info("当前传参是{}", name);
        return consumerService.get(name);
    }
    
    @GetMapping("/list")
    public List<String> list(){
        List<String> res = new ArrayList<>();
        res.add(DemoService1.class.getSimpleName());
        res.add(DemoService2.class.getSimpleName());
        res.add(DemoService3.class.getSimpleName());
        
        return res;
    }
}
