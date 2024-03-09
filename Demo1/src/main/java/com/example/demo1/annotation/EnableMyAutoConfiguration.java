package com.example.demo1.annotation;

import com.example.demo1.config.MyServiceRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyServiceRegistrar.class)
public @interface EnableMyAutoConfiguration {
}
