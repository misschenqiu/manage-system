package com.starda.managesystem.config.author;

import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.constant.Constant;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: CustomAccessDecisionManager
 * @Author: chenqiu
 * @Description: 全部用户权限信息管理
 * @Date: 2021/8/22 21:55
 * @Version: 1.0
 */

@Component
@Log4j2
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
    //configAttribute存放着 CustomFilterInvocationSecurityMetadataSource 过滤出来的 角色信息
        for (ConfigAttribute configAttribute : configAttributes) {
            // 是不是默认 角色 任意放行
            if(Constant.BaseStringInfoManage.DEFAULT_ROLE.equals(configAttribute.getAttribute())){
                // 是否是 匿名 用户
                if (authentication instanceof AnonymousAuthenticationToken){
                    log.info("匿名用户信息");
                    throw new AccessDeniedException(ExceptionEnums.NO_PERMISSION.getMessage());
                }else {
                    log.info("放行 该接口 给所有用户");
                    return;
                }
            }

            // 判断路径角色 通过 authentication 存在着登录后用户所有信息
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                log.info("用户的权限信息 authority -》{}" + authority.getAuthority());
                log.info("路径所需角色 configAttribute -》{}" + configAttribute.getAttribute());
                // 有对应的角色 放行
                if(authority.getAuthority().equals(configAttribute.getAttribute())){
                    log.info("用户拥有该路径权限" + o);
                    return;
                }
            }
        }

        throw new AccessDeniedException(ExceptionEnums.NO_PERMISSION.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
