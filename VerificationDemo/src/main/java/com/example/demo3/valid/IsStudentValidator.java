package com.example.demo3.valid;

import com.example.demo3.annotation.IsStudent;
import com.example.demo3.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsStudentValidator  implements ConstraintValidator<IsStudent, User> {
    
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null) {
            return true; // 如果目标对象为null，不进行验证，可以根据实际需求进行处理
        }
        
        if (user.getAge() > 25) {
            // 添加验证错误信息
            context.buildConstraintViolationWithTemplate("年龄不能超过25岁")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            return false;
        }
        
        return true;
    }
    
}
