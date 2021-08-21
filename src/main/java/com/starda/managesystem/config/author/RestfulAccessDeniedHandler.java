package com.starda.managesystem.config.author;

import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.JSONAuthentication;
import com.starda.managesystem.config.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: RestfulAccessDeniedHandler
 * @Author: chenqiu
 * @Description: 权限不足 处理方案
 * @Date: 2021/8/21 19:45
 * @Version: 1.0
 */

@Component
@Log4j2
public class RestfulAccessDeniedHandler extends JSONAuthentication implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.info(ExceptionEnums.NO_PERMISSION.getMessage());
        Result result=Result.error(ExceptionEnums.NO_PERMISSION.getCode(), ExceptionEnums.NO_PERMISSION.getMessage(), e.getMessage());
        this.WriteJSON(request,response,result);
    }
}
