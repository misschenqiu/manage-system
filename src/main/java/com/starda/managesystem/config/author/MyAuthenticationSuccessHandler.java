package com.starda.managesystem.config.author;

import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.jwtToken.JwtTokenUtil;
import com.starda.managesystem.constant.Constant;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: MyAuthenticationSuccessHandler
 * @Author: chenqiu
 * @Description: 登录成功
 * @Date: 2021/8/21 18:48
 * @Version: 1.0
 */

@Log4j2
@Component
public class MyAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户信息
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //将登录成功的授权信息置于springsecurity中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);

        // 返回数据
        HashMap data = new HashMap<String, String>() {{
            put("account", userDetails.getUsername());
            put("token", token);
        }};
        Result result = Result.success(data, Constant.BaseStringInfoManage.SUCCESS);
        this.WriteJSON(request, response, result);
    }
}
