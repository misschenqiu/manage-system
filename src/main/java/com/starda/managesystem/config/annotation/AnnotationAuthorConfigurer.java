package com.starda.managesystem.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.annotation
 * @ClassName: AnnotationAuthorConfigurer
 * @Author: chenqiu
 * @Description: 拦截注解实现类
 * @Date: 2021/8/23 22:58
 * @Version: 1.0
 */

@Configuration
public class AnnotationAuthorConfigurer implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册loginUserMethodArgumentResolver的参数分解器
        argumentResolvers.add(loginUserMethodArgumentResolver());
    }

    @Bean
    public CurrentUserMethodArgumentResolver loginUserMethodArgumentResolver(){
        return new CurrentUserMethodArgumentResolver();
    }
}
