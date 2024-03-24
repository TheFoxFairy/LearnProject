package com.example.autowireddemo.core.support;

import com.example.autowireddemo.core.annotation.MyAutowired;
import com.example.autowireddemo.core.utils.ProxyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Slf4j
public class MyInjectionBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public MyInjectionBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        // 获取当前 bean 的 Class 类型
        Class<?> clazz = bean.getClass();
        // 遍历当前 bean 类及其父类中的所有字段
        do {
            for (final Field field : clazz.getDeclaredFields()) {
                // 检查字段是否标记了 @MyAutowired 注解
                final MyAutowired annotation = AnnotationUtils.findAnnotation(field, MyAutowired.class);
                if (Objects.nonNull(annotation)) {
                    // 让字段可访问，以便设置其值
                    ReflectionUtils.makeAccessible(field);
                    // 设置字段的值为通过 processInjectionPoint 处理后的值
                    ReflectionUtils.setField(field, bean, processInjectionPoint(field.getType()));
                }
            }
            // 遍历当前 bean 类及其父类中的所有方法
            for (final Method method : clazz.getDeclaredMethods()) {
                // 检查方法是否标记了 @MyAutowired 注解
                final MyAutowired annotation = AnnotationUtils.findAnnotation(method, MyAutowired.class);
                if (Objects.nonNull(annotation)) {
                    // 获取方法的参数类型数组
                    final Class<?>[] paramTypes = method.getParameterTypes();
                    // 确保方法只有一个参数
                    if (Objects.equals(paramTypes.length, 1)) {
                        throw new BeanDefinitionStoreException(
                                "Method " + method + " doesn't have exactly one parameter.");
                    }
                    // 让方法可访问，以便调用
                    ReflectionUtils.makeAccessible(method);
                    // 调用方法，并将参数设置为通过 processInjectionPoint 处理后的值
                    ReflectionUtils.invokeMethod(method, bean, processInjectionPoint(paramTypes[0]));
                }
            }
            // 继续处理父类
            clazz = clazz.getSuperclass();
        } while (clazz != null); // 直到处理完所有父类为止
        // 返回处理后的 bean
        return bean;
    }

    // 创建代理类，在具体方法执行前后输出一个日志
    protected <T> T processInjectionPoint(final Class<T> injectionType) {
        return ProxyUtil.newProxyInstance(injectionType, (proxy, method, args) -> {
            log.info("do before " + method.getName() + " | " + Thread.currentThread());
            try {
                Object obj = applicationContext.getBean(injectionType);
                return method.invoke(obj, args);
            } finally {
                log.info("do after " + method.getName() + " | " + Thread.currentThread());
            }
        }, var -> true);
    }
}
