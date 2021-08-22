package com.starda.managesystem.config.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.annotation
 * @ClassName: AnnotationAuthor
 * @Author: chenqiu
 * @Description: 自定义注解获取用户信息
 * @Date: 2021/8/23 0:22
 * @Version: 1.0
 */

@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationAuthor {
}
