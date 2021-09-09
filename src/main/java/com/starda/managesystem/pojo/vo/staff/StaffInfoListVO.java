package com.starda.managesystem.pojo.vo.staff;

import com.baomidou.mybatisplus.annotation.TableField;
import com.starda.managesystem.common.AESEncryptHandler;
import com.starda.managesystem.pojo.enums.UserTypeEnums;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.staff
 * @ClassName: StaffInfoListVO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/28 11:29
 * @Version: 1.0
 */

@Data
public class StaffInfoListVO {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

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
     * 职位
     */
    private String position;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建账号
     */
    private String accountName;

    /**
     * 归宿地
     */
    private String address;

}
