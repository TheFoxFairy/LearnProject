package com.example.interfaceunifiedcalldemo.enums;

import com.example.interfaceunifiedcalldemo.service.DemoService1;
import com.example.interfaceunifiedcalldemo.service.DemoService2;
import com.example.interfaceunifiedcalldemo.service.DemoService3;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum RequestEnum {
    Demo1(DemoService1.class, 1, DemoService1.class.getSimpleName()),
    Demo2(DemoService2.class, 2, DemoService2.class.getSimpleName()),
    Demo3(DemoService3.class, 3, DemoService3.class.getSimpleName());
    
    private final Class<?> clazz;
    private final Integer type;
    private final String name;
    
    public static RequestEnum getByType(Integer type) {
        for (RequestEnum requestEnum : RequestEnum.values()) {
            if (requestEnum.getType().equals(type)) {
                return requestEnum;
            }
        }
        return null; // or throw an exception indicating type not found
    }
    
    public static RequestEnum getByName(String name) {
        for (RequestEnum requestEnum : RequestEnum.values()) {
            if (requestEnum.getName().equals(name)) {
                return requestEnum;
            }
        }
        return null; // or throw an exception indicating name not found
    }
}
