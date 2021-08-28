package com.starda.managesystem.pojo.po.staff;

import com.starda.managesystem.pojo.enums.UserTypeEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.staff
 * @ClassName: StaffInfoUpdatePO
 * @Author: chenqiu
 * @Description: 修改员工信息
 * @Date: 2021/8/28 15:21
 * @Version: 1.0
 */

@Data
public class StaffInfoUpdatePO {

    @NotNull(message = "员工id不能为空")
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private UserTypeEnums sex;

    /**
     * 身高
     */
    private Double userHeight;

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

}
