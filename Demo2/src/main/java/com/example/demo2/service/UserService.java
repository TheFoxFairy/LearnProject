package com.example.demo2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo2.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    void getAllUserByUserName(String userName);

}
