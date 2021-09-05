package com.starda.managesystem.pojo.vo.business;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.business
 * @ClassName: TsakInfoRemarkVO
 * @Author: chenqiu
 * @Description: 业务详情说明
 * @Date: 2021/9/5 22:11
 * @Version: 1.0
 */

@Data
public class TaskInfoRemarkVO {

    private Integer id;

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
