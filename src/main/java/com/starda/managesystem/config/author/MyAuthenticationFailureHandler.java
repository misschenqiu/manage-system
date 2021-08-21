package com.starda.managesystem.config.author;

import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: MyAuthenticationFailureHandler
 * @Author: chenqiu
 * @Description: 登录失败 处理
 * @Date: 2021/8/21 18:51
 * @Version: 1.0
 */

@Log4j2
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends JSONAuthentication implements AuthenticationFailureHandler {

    /**
     * 错误信息处理
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        /**
         * 对抛出的异常进行判断
         */
        Result result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            log.info(ExceptionEnums.USER_ACCOUNT_EXPIRED.getMessage());
            result = Result.error(ExceptionEnums.USER_ACCOUNT_EXPIRED.getCode(), ExceptionEnums.USER_ACCOUNT_EXPIRED.getMessage(), e.getMessage());
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            log.info(ExceptionEnums.USER_CREDENTIALS_ERROR.getMessage());
            result = Result.error(ExceptionEnums.USER_CREDENTIALS_ERROR.getCode(), ExceptionEnums.USER_CREDENTIALS_ERROR.getMessage(), e.getMessage());
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            log.info(ExceptionEnums.USER_CREDENTIALS_EXPIRED.getMessage());
            result = Result.error(ExceptionEnums.USER_CREDENTIALS_EXPIRED.getCode(), ExceptionEnums.USER_CREDENTIALS_EXPIRED.getMessage(), e.getMessage());
        } else if (e instanceof DisabledException) {
            //账号不可用
            log.info(ExceptionEnums.USER_ACCOUNT_DISABLE.getMessage());
            result = Result.error(ExceptionEnums.USER_ACCOUNT_DISABLE.getCode(), ExceptionEnums.USER_ACCOUNT_DISABLE.getMessage(), e.getMessage());
        } else if (e instanceof LockedException) {
            //账号锁定
            log.info(ExceptionEnums.USER_ACCOUNT_DISABLE.getMessage());
            result = Result.error(ExceptionEnums.USER_ACCOUNT_LOCKED.getCode(), ExceptionEnums.USER_ACCOUNT_LOCKED.getMessage(), e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = Result.error(ExceptionEnums.USER_ACCOUNT_NOT_EXIST.getCode(), ExceptionEnums.USER_ACCOUNT_NOT_EXIST.getMessage(), e.getMessage());
        } else {
            //其他错误
            result = Result.error(ExceptionEnums.DEFAULT.getCode(), ExceptionEnums.DEFAULT.getMessage(), e.getMessage());
        }
        this.WriteJSON(request, response, result);
    }
}
