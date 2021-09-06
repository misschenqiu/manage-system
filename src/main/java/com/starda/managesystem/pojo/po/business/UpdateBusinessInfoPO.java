package com.starda.managesystem.pojo.po.business;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.business
 * @ClassName: UpdateBusinessInfoPO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/5 23:36
 * @Version: 1.0
 */

@Data
public class UpdateBusinessInfoPO {

    private Integer id;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 描述
     */
    private String remark;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 合作单位
     */
    private Integer companyId;

    /**
     * 单位名
     */
    private String companyName;

    /**
     * 保险单号
     */
    private String insuranceNumber;

    /**
     * 对应人
     */
    @NotBlank(message = "业务对应人不能为空")
    private String dockPeople;

    /**
     * 对应人电话
     */
    private String dockPhone;

    /**
     * 图片说明
     */
    private String businessImg;

    /**
     * 该单金额
     */
    private String money;

    /**
     * 保险公司
     */
    private String insuranceCompany;

    /**
     * 保险联系人
     */
    private String insurancePeople;

    /**
     * 保险联系电话
     */
    private String insurancePhone;


}
