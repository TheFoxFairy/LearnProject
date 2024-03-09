package com.example.demo1.config;

import com.example.demo1.annotation.MyService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;


public class ClassPathMyServiceScanner {

    private final BeanDefinitionRegistry registry;
    private final String basePackage;

    public ClassPathMyServiceScanner(BeanDefinitionRegistry registry) {
        this(registry, "");
    }

    /**
     * 构造方法
     *
     * @param registry     Bean定义注册器
     * @param basePackage  基础包路径
     */
    public ClassPathMyServiceScanner(BeanDefinitionRegistry registry, String basePackage) {
        this.registry = registry;
        this.basePackage = basePackage;
    }

    /**
     * 扫描并注册带有@MyService注解的类
     *
     * @throws IOException 如果扫描过程中发生I/O异常
     */
    public void scan() throws IOException {
        // 创建ClassPathScanningCandidateComponentProvider对象，用于扫描候选组件（类）
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

        // 创建TypeFilter对象，用于过滤带有@MyService注解的类型
        TypeFilter myServiceFilter = new AnnotationTypeFilter(MyService.class);

        // 将过滤器添加到扫描器中
        scanner.addIncludeFilter(myServiceFilter);

        // 构建基于包路径的搜索路径
        String packageSearchPath = "classpath*:" + basePackage.replace('.', '/') + "/**";

        // 获取匹配搜索路径的资源列表
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);

        // 遍历资源列表，解析类名并注册为Bean定义
        for (Resource resource : resources) {
            String className = resolveClassName(resource);
            if (className != null) {
                // 创建一个通用的Bean定义实例
                BeanDefinition beanDefinition = new GenericBeanDefinition();

                // 设置Bean定义的类名为解析得到的类名
                beanDefinition.setBeanClassName(className);

                // 将Bean定义注册到Bean定义注册器中
                registry.registerBeanDefinition(className, beanDefinition);
            }
        }
    }

    /**
     * 解析资源的类名
     *
     * @param resource 资源对象
     * @return 类名
     */
    private String resolveClassName(Resource resource) {
        try {
            // 使用SimpleMetadataReaderFactory创建MetadataReader
            MetadataReader metadataReader = new SimpleMetadataReaderFactory().getMetadataReader(resource);

            // 判断类的注解元数据中是否包含@MyService注解
            if (metadataReader.getAnnotationMetadata().hasAnnotation(MyService.class.getName())) {
                // 返回类的全限定名作为类名
                return metadataReader.getClassMetadata().getClassName();
            }
        } catch (Exception e) {
            // 处理异常情况
        }
        return null;
    }
}