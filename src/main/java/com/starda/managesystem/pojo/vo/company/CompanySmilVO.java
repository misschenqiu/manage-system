package com.starda.managesystem.pojo.vo.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.company
 * @ClassName: CompanySmilVO
 * @Author: chenqiu
 * @Description: 公司简约版
 * @Date: 2021/8/31 23:45
 * @Version: 1.0
 */

@Data
public class CompanySmilVO {

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

}
