package com.starda.managesystem.pojo.vo.staff;

import com.baomidou.mybatisplus.annotation.TableField;
import com.starda.managesystem.pojo.enums.UserTypeEnums;
import com.starda.managesystem.pojo.vo.role.RoleListVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo
 * @ClassName: StaffInfoVO
 * @Author: chenqiu
 * @Description: 员工信息
 * @Date: 2021/8/28 9:27
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInfoVO {

    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 归属地
     */
    private String address;

    /**
     * 编码
     */
    private String addressCode;

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
     * 创建时间
     */
    private String createTime;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 账号id
     */
    private Integer userId;

    /**
     * 职位
     */
    private String position;

    /**
     * 描述
     */
    private String remark;

    /**
     * 角色信息
     */
    private List<RoleListVO> roleList;

}
