package com.starda.managesystem.pojo.vo;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo
 * @ClassName: AddressVO
 * @Author: chenqiu
 * @Description: 地址管理实体类
 * @Date: 2021/8/24 14:06
 * @Version: 1.0
 */

@Data
public class AddressVO {

    /**
     * 地址名
     */
    private String addressName;

    /**
     * 地址编码
     */
    private String addressCode;

}
