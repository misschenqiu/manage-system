package com.starda.managesystem.config.author;

import com.starda.managesystem.config.jwtToken.JwtAuthenticationTokenFilter;
import com.starda.managesystem.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: WebSecurityConfig
 * @Author: chenqiu
 * @Description: security 安全配置文件
 * @Date: 2021/8/20 17:05
 * @Version: 1.0
 */

/**
 * param @EnableGlobalMethodSecurity 开启权限注解控制
 * @author chenqiu
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用这么一个实现类，获取加密加盐的密码
        //而且每次运行就算是相同的字符串，生成的密码也是不一样的
        return new BCryptPasswordEncoder();
    }

    /**
     * 修改密码的加密方式
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 登录认证
     */
    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 异常处理
     */
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint ;

    /**
     * 登录成功的处理方案
     */
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * 登录失败的处理方案
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 注销成功的处理方案
     */
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * 模拟用户账号被挤下线的处理方案
     */
    @Autowired
    private MySessionInformationExpiredStrategy mySessionInformationExpiredStrategy;

    /**
     * 权限不足
     */
    @Resource
    private RestfulAccessDeniedHandler accessDeniedHandler;

    /**
     * jwt
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //替换默认的登录方式为自定义的登录认证方式
        auth.userDetailsService(userDetailsServiceImpl)
                //替换默认的密码认证方式
                .passwordEncoder(passwordEncoder);
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //第1步：解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        //第2步：让Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();
/**
 * 在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
 */
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //登录
        http.formLogin()
                //允许所有用户
                .permitAll()
                //登录成功
                .successHandler(myAuthenticationSuccessHandler)
                //登录失败
                .failureHandler(myAuthenticationFailureHandler);
        //注销
        http.logout()
                //允许所有用户
                .permitAll()
                //注销成功
                .logoutSuccessHandler(myLogoutSuccessHandler)
                //登出之后删除cookie
                .deleteCookies(Constant.BaseStringInfoManage.COOKIES);

        //限制账号的会话管理
        http.sessionManagement()
                //同一账号同时登录最大用户数
                .maximumSessions(1)
                //如果为true,那么之后不能再登录，如果为false，登录之后会将之前的账号挤下线
                .maxSessionsPreventsLogin(false)
                //会话信息过期策略会话信息过期策略(账号被挤下线)
                .expiredSessionStrategy(mySessionInformationExpiredStrategy);

        //异常处理(权限拒绝，登录失效等)
        http.exceptionHandling()
                //用来解决匿名用户访问无权限资源时的异常 因为是通过authenticated认证的
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.authorizeRequests()
                .antMatchers("/bb").hasAuthority("home:index")
                .anyRequest().authenticated();
    }

}
