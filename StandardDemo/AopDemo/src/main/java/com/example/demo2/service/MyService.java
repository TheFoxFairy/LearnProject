package com.example.demo2.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Cacheable("myCache")
    public String getCachedData(String key) {
        // 从数据库或其他数据源获取数据的逻辑
        return "Cached Data";
    }
}