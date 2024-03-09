package com.example.demo3.annotation;

import com.example.demo3.valid.IsStudentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE}) // 用于描述类、接口(包括注解类型) 或enum声明
@Documented
@Constraint(validatedBy = IsStudentValidator.class)
public @interface IsStudent {
    // 校验未通过时的返回信息
    String message() default "一般情况下年龄大于25，就基本不是学生了，必须小于等于25";
    
    // 以下两行为固定模板
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
