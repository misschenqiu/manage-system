package com.starda.managesystem.pojo.po.company;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.company
 * @ClassName: CompanyUpdatePO
 * @Author: chenqiu
 * @Description: 修改单位信息
 * @Date: 2021/8/30 21:00
 * @Version: 1.0
 */

@Data
public class CompanyUpdatePO {

    private Integer id;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 所属地
     */
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
