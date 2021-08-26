package com.starda.managesystem.config.author;

import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: MyAuthenticationEntryPoint
 * @Author: chenqiu
 * @Description: 用来解决匿名用户访问无权限资源时的异常
 * @Date: 2021/8/21 20:54
 * @Version: 1.0
 */

@Component("myAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint extends JSONAuthentication implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //结果处理：账号未登录
        Result result = Result.ok().error(ExceptionEnums.USER_NOT_LOGIN.getCode(), ExceptionEnums.USER_NOT_LOGIN.getMessage(), e.getMessage());
        //通过json输出
        this.WriteJSON(request, response, result);
    }
}
