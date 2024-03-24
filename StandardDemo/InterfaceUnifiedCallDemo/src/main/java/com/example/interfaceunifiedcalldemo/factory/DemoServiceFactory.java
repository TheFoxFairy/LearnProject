package com.example.interfaceunifiedcalldemo.factory;

import com.example.interfaceunifiedcalldemo.enums.RequestEnum;
import com.example.interfaceunifiedcalldemo.service.DemoService1;
import com.example.interfaceunifiedcalldemo.service.DemoService2;
import com.example.interfaceunifiedcalldemo.service.DemoService3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class DemoServiceFactory {
    
    @Resource
    private ApplicationContext applicationContext;
    
    public String getInstance(RequestEnum requestEnum) {
        log.info("当前枚举信息为{}", requestEnum);
        Object bean = this.getBean(this.getBeanName(requestEnum.getClazz()), requestEnum.getClazz());
        switch (requestEnum.getName()) {
            case "DemoService1":
                DemoService1 demoService1 = (DemoService1) bean;
                return demoService1.say();
            case "DemoService2":
                DemoService2 demoService2 = (DemoService2) bean;
                return demoService2.say();
            case "DemoService3":
                DemoService3 demoService3 = (DemoService3) bean;
                return demoService3.say();
            default:
                throw new IllegalArgumentException("Invalid service name: " + requestEnum.getName());
        }
    }
    
    public Object getBean(String name, Class<?> clazz){
        return applicationContext.getBean(name, clazz);
    }
    
    public String getBeanName(Class<?> clazz) {
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);
        if (beanNames.length > 0) {
            return beanNames[0]; // Assuming there's only one bean of the given type
        } else {
            return null; // No bean found for the given type
        }
    }
}

