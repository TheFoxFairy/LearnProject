package com.example.demo2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo2.entity.UserEntity;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService{
    @Override
    public void getAllUserByUserName(String username) {

        // 1. 通过 map 进行筛选使用
        Map<String, Object> map = new HashMap<>();
        map.put("user_name",username);
        this.baseMapper.selectByMap(map);

        // 2. 通过 QueryWrapper 进行筛选使用
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getUserName, username);
        this.baseMapper.selectOne(queryWrapper);
    }
}
