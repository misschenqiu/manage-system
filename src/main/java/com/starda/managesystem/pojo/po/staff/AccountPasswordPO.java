package com.starda.managesystem.pojo.po.staff;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.staff
 * @ClassName: AccountPasswordPO
 * @Author: chenqiu
 * @Description: 修改密码
 * @Date: 2021/8/28 15:31
 * @Version: 1.0
 */

@Data
public class AccountPasswordPO {

    /**
     * 旧密码
     */
    @NotBlank(message = "请输出旧密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "请输出新密码")
    private String newPassword;

    /**
     * 确认密码
     */
    @NotBlank(message = "请确认密码")
    private String againPassword;

}
