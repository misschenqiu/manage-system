package com.starda.managesystem.config.author;

import com.baomidou.mybatisplus.annotation.TableField;
import com.starda.managesystem.common.AESEncryptHandler;
import com.starda.managesystem.pojo.SysRole;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.author
 * @ClassName: UserVO
 * @Author: chenqiu
 * @Description: 封装信息类 可以自定义
 * @Date: 2021/8/22 21:34
 * @Version: 1.0
 */

@Data
@ToString
@EqualsAndHashCode
public class UserVO implements UserDetails, CredentialsContainer {

    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 账号所属地
     */
    private String address;

    /**
     * 所属地编码
     */
    private String address_code;

    /**
     * 员工共id
     */
    private Integer staffId;

    /**
     * 员工名字
     */
    private String staffName;

    /**
     * 员工头像
     */
    private String staffImg;

    /**
     * 角色信息
     */
    private String roleListString;

    /**
     * 手机序列号
     */
    private String phoneSerial;

    /**
     * 角色信息
     */
    List<GrantedAuthority> authorities;

    public UserVO(Integer id, String account, String password, String phone,
                  String address, String address_code, Integer staffId,
                  String staffName, String staffImg, List<GrantedAuthority> authorities,
                  String roleListString) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.address_code = address_code;
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffImg = staffImg;
        this.authorities = authorities;
        this.roleListString = roleListString;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
