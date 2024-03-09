package com.example.demo1.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class MyServiceRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 注册@MyService注解标注的类的Bean定义
     *
     * @param importingClassMetadata 注解元数据
     * @param registry               Bean定义注册器
     */
    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 创建ClassPathMyServiceScanner对象，传入Bean定义注册器
        ClassPathMyServiceScanner scanner = new ClassPathMyServiceScanner(registry);

        // 扫描并注册带有@MyService注解的类
        scanner.scan();
    }
}
