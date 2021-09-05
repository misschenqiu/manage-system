package com.starda.managesystem.pojo.po.company;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.company
 * @ClassName: CompanyInsertPO
 * @Author: chenqiu
 * @Description: 单位信息
 * @Date: 2021/8/30 20:22
 * @Version: 1.0
 */

@Data
public class CompanyInsertPO {

    /**
     * 单位名称
     */
    @NotBlank(message = "单位名称不能为空")
    private String companyName;

    /**
     * 所属地
     */
    @NotBlank(message = "所属地址不能为空")
    private String address;

    /**
     * 所属地编码
     */
    private String addressCode;

    /**
     * 描述
     */
    private String remark;

    /**
     * 联系电话
     */
    private String companyPhone;

    /**
     * 联系人名
     */
    private String companyUser;

}
