package com.starda.managesystem.pojo.dto;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto
 * @ClassName: CompanySmilDTO
 * @Author: chenqiu
 * @Description: 关联查询单位信息
 * @Date: 2021/9/1 0:02
 * @Version: 1.0
 */

@Data
public class CompanySmilDTO {

    private Integer id;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 联系电话
     */
    private String companyPhone;

    /**
     * 联系人名
     */
    private String companyUser;

    /**
     * 消息id
     */
    private Integer reminderId;

}
