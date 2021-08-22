package com.starda.managesystem.config.author;

import cn.hutool.cache.CacheUtil;
import com.starda.managesystem.common.LocalCache;
import com.starda.managesystem.common.SecurityPasswordCommon;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.constant.Constant;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: MyLogoutSuccessHandler
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/21 20:33
 * @Version: 1.0
 */

@Component("myLogoutSuccessHandler")
@Log4j2
public class MyLogoutSuccessHandler extends JSONAuthentication implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = null;
        // 删除 token 信息
        String headerToken = request.getHeader(Constant.BaseStringInfoManage.HEADER);
        log.info("headerToken = " + headerToken);
        log.info("request getMethod = " + request.getMethod());

        if (!StringUtils.isEmpty(headerToken)) {
            //postMan测试时，自动加入的前缀，要去掉。
            String tokenKey = headerToken.replace("Bearer", "").trim();
            //获取用户信息
            LocalCache.remove(SecurityPasswordCommon.generateKey(tokenKey));
            // 返回数据
            result = Result.success(ExceptionEnums.CANCELLATION.getMessage());
        }else {
            result = Result.error(ExceptionEnums.USER_ACCOUNT_CANCELLATION.getCode(), ExceptionEnums.USER_ACCOUNT_CANCELLATION.getMessage(), Constant.ResultCodeMessage.FAIL);
        }
        this.WriteJSON(request, response, result);
    }
}
