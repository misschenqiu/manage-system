package com.starda.managesystem.config.author;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
import com.starda.managesystem.service.ISysMenuService;
import com.starda.managesystem.service.IUserInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: UserDetalisServiceImpl
 * @Author: chenqiu
 * @Description: 用户信息 认证实现类
 * @Date: 2021/8/21 8:09
 * @Version: 1.0
 */

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 用户登录信息
     *
     * @param userAccount 用户账号
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {

        // 1.获取用户信息
        AccountInfoDTO account = userInfoService.getAccountInfo(userAccount);
        UserVO userVO = BeanUtil.copyProperties(account, UserVO.class);
        // 系统角色
        userVO.setRoleListString(JSONObject.toJSONString(account.getRoleList()));
        if (null == account) {
            log.info("没有该账号的信息!");
            throw new UsernameNotFoundException(String.format(ExceptionEnums.USER_ACCOUNT_NOT_EXIST.getMessage(), userAccount));
        }

        // 无权限
        if(null == account.getRoleList() || account.getRoleList().isEmpty()){
            userVO.setAuthorities(new ArrayList<>());
            return userVO;
        }

        // 2.获取权限信息
//        List<SysMenu> sysMenuList = menuService.getMenuInfoList(account.getRoleList().parallelStream().map(SysRole::getId).collect(Collectors.toList()));
        // 3.封装权限信息
        // 3.1创建权限集
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 3.2 添加权限集合
        account.getRoleList().forEach(role -> {
            //通过GrantedAuthority的子类去存储权限码
            if (StrUtil.isBlank(role.getRole_code())) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole_code());
                //再将权限存在到权限集中
                authorities.add(authority);
            }

        });

        // 封装数据
        userVO.setAuthorities(authorities);
//        return new User(account.getAccount(), account.getPassword(), authorities);
        return userVO;
    }
}
