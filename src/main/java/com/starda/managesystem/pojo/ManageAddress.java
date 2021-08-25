package com.starda.managesystem.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo
 * @ClassName: ManageAddress
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/24 20:35
 * @Version: 1.0
 */

@Data
public class ManageAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名字
     */
    private String addressName;

    /**
     * 编码
     */
    private String addressCode;

    /**
     * 是否可用
     */
    private Integer status;

}
