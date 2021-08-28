package com.starda.managesystem.pojo.po.staff;

import com.starda.managesystem.pojo.enums.UserTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.staff
 * @ClassName: StaffInfoPO
 * @Author: chenqiu
 * @Description: 添加员工
 * @Date: 2021/8/28 10:37
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInfoPO {

    private Integer id;

    /**
     * 账号
     */
    @NotBlank(message = "账号信息不能为空")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 归属地
     */
    @NotBlank(message = "归属地不能为空")
    private String address;

    /**
     * 编码
     */@NotBlank(message = "归属编码不能为空")
    private String addressCode;

    /**
     * 用户名
     */
    @NotBlank(message = "员工名不能为空")
    private String userName;

    /**
     * 性别
     */
    private UserTypeEnums sex;

    /**
     * 身高
     */
    private Integer userHeight;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 职位
     */
    private String position;

    /**
     * 描述
     */
    private String remark;

    /**
     * 角色id
     */
    private List<Integer> roleIds;

}
