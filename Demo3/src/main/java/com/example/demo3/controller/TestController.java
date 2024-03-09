package com.example.demo3.controller;

import com.example.demo3.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class TestController {
    
    @GetMapping()
    public ResponseEntity<Object> test(@Validated User user, BindingResult bindingResult){
        
        String result = "success";
        
        if (bindingResult.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (int i = 0, len = errors.size(); i < len; i++) {
                ObjectError error = errors.get(i);
                msg.append(error.getObjectName()).append("存在错误:").append(error.getDefaultMessage()).append("\n");
            }
            result = msg.toString();
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        
        return ResponseEntity.ok(user);
    }
}
