package com.example.demo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo2.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
}
