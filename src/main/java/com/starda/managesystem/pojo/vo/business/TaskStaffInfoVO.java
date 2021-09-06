package com.starda.managesystem.pojo.vo.business;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.business
 * @ClassName: TaskStaffInfoVO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/6 23:50
 * @Version: 1.0
 */

@Data
public class TaskStaffInfoVO {

    private Integer id;

    /**
     * 员工名称
     */
    private String staffName;

    /**
     * 员工头像
     */
    private String staffHeadImg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注图片
     */
    private String remarkImg;

    /**
     * 业务详情id
     */
    private Integer businessInfoId;


}
