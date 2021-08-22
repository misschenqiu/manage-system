package com.starda.managesystem.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po
 * @ClassName: AccountPO
 * @Author: chenqiu
 * @Description: 账号信息
 * @Date: 2021/8/21 23:44
 * @Version: 1.0
 */

@Data
public class AccountPO {

    /**
     * 账号 / 电话
     */
    @NotBlank
    private String account;

    /**
     * 密码
     */
    @NotBlank
    private String password;

}
