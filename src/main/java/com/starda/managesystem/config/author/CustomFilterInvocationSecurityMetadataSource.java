package com.starda.managesystem.config.author;

import com.starda.managesystem.common.CommonConfigura;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.pojo.dto.MenusToRoleInfoDTO;
import com.starda.managesystem.pojo.dto.RoleInfoListDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: CustomFilterInvocationSecurityMetadataSource
 * @Author: chenqiu
 * @Description: 角色过滤
 * @Date: 2021/8/22 21:57
 * @Version: 1.0
 */

@Component
@Log4j2
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     *  ant 风格路劲匹配器
     */
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private CommonConfigura commonConfigura;

    /**
     * 匹配角色
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 1.所有路径
        List<MenusToRoleInfoDTO> menuList = this.commonConfigura.getMenuList();
        // 2.获取请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        for (MenusToRoleInfoDTO menusToRoleInfoDTO : menuList) {
            // 判断请求 url 与 数据库中的 url 是否可以匹配
            if(pathMatcher.match(menusToRoleInfoDTO.getUrl(), requestUrl)){
                List<RoleInfoListDTO> roleList = menusToRoleInfoDTO.getRoleList();
                String[] role = new String[roleList.size()];
                for (int i = 0; i < roleList.size(); i++) {
                    role[i] = roleList.get(i).getRole_code();
                }
                // 将url对应赛选出来的角色 返回出去
                log.info("角色信息： role->{}" + role);
                return SecurityConfig.createList(role);
            }
        }
        // 如果没有匹配上， 数据库里面没有该路径 或者该路径上没有匹配角色设置一个默认角色
        log.info("角色信息： role->{ROLE_def}");
        return SecurityConfig.createList(Constant.BaseStringInfoManage.DEFAULT_ROLE);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
