package com.starda.managesystem.pojo.po.company;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.company
 * @ClassName: CompanyQueryPO
 * @Author: chenqiu
 * @Description: 查询单位信息
 * @Date: 2021/8/30 20:45
 * @Version: 1.0
 */

@Data
public class CompanyQueryPO {

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 电话号码
     */
    private String companyPhone;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不饿能为空")
    private Integer currentPage;

    /**
     * 显示条数
     */
    @NotNull(message = "显示条数不能为空")
    private Integer pageSize;

}
