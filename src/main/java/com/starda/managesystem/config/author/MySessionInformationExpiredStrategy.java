package com.starda.managesystem.config.author;

import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: MySessionInformationExpiredStrategy
 * @Author: chenqiu
 * @Description: 账号 被挤下来 账号被挤下线的处理方案
 * @Date: 2021/8/21 20:29
 * @Version: 1.0
 */

@Component
public class MySessionInformationExpiredStrategy extends JSONAuthentication implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        //生成request
        HttpServletRequest request = sessionInformationExpiredEvent.getRequest();
        //生成response
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        Result result = Result.error(ExceptionEnums.USER_ACCOUNT_USE_BY_OTHERS.getCode(), ExceptionEnums.USER_ACCOUNT_USE_BY_OTHERS.getMessage(), "你的账号在异处登录！");
        this.WriteJSON(request, response, result);
    }
}
