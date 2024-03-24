package com.example.demo3.entity;


import com.example.demo3.annotation.IsStudent;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@IsStudent(message = "年龄大于25了，应该不是学生了")
public class User {

    @NotNull
    private String name;
    
    private Integer age;
}
