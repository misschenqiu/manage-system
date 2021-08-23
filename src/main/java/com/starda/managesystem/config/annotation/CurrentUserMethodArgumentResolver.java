package com.starda.managesystem.config.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.starda.managesystem.common.LocalCache;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.config.jwtToken.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.annotation
 * @ClassName: CurrentUserMethodArgumentResolver
 * @Author: chenqiu
 * @Description: 绑定自定义注解 实现类
 * @Date: 2021/8/23 20:45
 * @Version: 1.0
 */
@Configuration
@Log4j2
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断是否支持使用  自定义注解的参数 是否满足条件
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //判断是否支持使用@CurrentUser注解的参数; 如果该参数注解有@CurrentUser且参数类型是User
        return parameter.getParameterAnnotation(AnnotationAuthor.class) != null && parameter.getParameterType() == UserVO.class ;
    }

    /**
     * 注入参数值 取值 填入数据
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取token 信息
        String headerToken =  webRequest.getHeader("Authorization");
        if (!StringUtils.isEmpty(headerToken)) {
            //postMan测试时，自动加入的前缀，要去掉。
            String tokenKey = headerToken.replace("Bearer", "").trim();
            log.info("get attribute header ----" + headerToken);
            String token = (String)LocalCache.get(tokenKey, false);

            // 解析token
            if(StrUtil.isBlank(token)){
                return null;
            }
            // 获取用户信息
            UserVO userDetails = BeanUtil.toBean(JwtTokenUtil.getClaimsFromToken(token), UserVO.class);
            return userDetails;
        }
        return null;
    }
}