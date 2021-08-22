package com.starda.managesystem.config.jwtToken;

import cn.hutool.core.util.StrUtil;
import com.starda.managesystem.common.LocalCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.jwtToken
 * @ClassName: JwtAuthenticationTokenFilter
 * @Author: chenqiu
 * @Description: token 拦截器
 * @Date: 2021/8/20 23:44
 * @Version: 1.0
 */

@Component("jwtAuthenticationTokenFilter")
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 请求投中 特定参数
     */
    private final String header = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String headerToken = request.getHeader(header);
        log.info("headerToken = " + headerToken);
        log.info("request getMethod = " + request.getMethod());

        if (!StringUtils.isEmpty(headerToken)) {
            //postMan测试时，自动加入的前缀，要去掉。
            String tokenKey = headerToken.replace("Bearer", "").trim();
            log.info("token = " + tokenKey);

            //判断令牌是否过期，默认是一周
            //比较好的解决方案是：
            // 从本地缓存里面获取数据
            String token = (String) LocalCache.get(tokenKey, false);
            if(StrUtil.isBlank(token)){
                chain.doFilter(request, response);
                return;
            }
            //登录成功获得token后，将token存储到数据库（redis）
            //将数据库版本的token设置过期时间为15~30分钟
            //如果数据库中的token版本过期，重新刷新获取新的token
            //注意：刷新获得新token是在token过期时间内有效。
            //如果token本身的过期（1周），强制登录，生成新token。
            boolean check = false;
            try {
                check = this.jwtTokenUtil.isTokenExpired(token);
            } catch (Exception e) {
                log.error("令牌已过期，请重新登录。" + e.getMessage());
                new Throwable("令牌已过期，请重新登录。"+ e.getMessage());
            }
            if (!check){
                //通过令牌获取用户名称
                String account = jwtTokenUtil.getUsernameFromToken(token);
                log.info("account = " + account);

                //判断用户不为空，且SecurityContextHolder授权信息还是空的
                if (account != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //通过用户信息得到UserDetails
                    UserDetails userDetails = userDetailsService.loadUserByUsername(account);
                    if(null == userDetails){
                        log.info("没有查到相关用户信息！");
                        new Throwable("验证token无效");
                    }
                    //验证令牌有效性
                    boolean validata = false;
                    try {
                        validata = jwtTokenUtil.validateToken(token, userDetails);
                    }catch (Exception e) {
                        log.error("验证token无效:" + e.getMessage());
                        new Throwable("验证token无效:"+e.getMessage());
                    }
                    if (validata) {
                        // 将用户信息存入 authentication，方便后续校验
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        //
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(request, response);

    }
}
