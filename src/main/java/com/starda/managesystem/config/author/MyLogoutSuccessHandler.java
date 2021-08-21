package com.starda.managesystem.config.author;

import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

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
public class MyLogoutSuccessHandler extends JSONAuthentication implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = Result.success(ExceptionEnums.CANCELLATION.getMessage());
        this.WriteJSON(request, response, result);
    }
}
