package com.starda.managesystem.pojo.po.staff;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.staff
 * @ClassName: AccountListPO
 * @Author: chenqiu
 * @Description: 账号列表
 * @Date: 2021/8/28 17:44
 * @Version: 1.0
 */

@Data
public class AccountListPO {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 显示条数
     */
    private Integer pageSize;

    /**
     * 地区编码
     */
    private String addressCode;

    /**
     * 账号
     */
    private String accountName;

    /**
     * 最大账号
     */
    private Integer accountId;

}
