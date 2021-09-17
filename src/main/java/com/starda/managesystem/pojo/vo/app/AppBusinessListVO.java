package com.starda.managesystem.pojo.vo.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.app
 * @ClassName: AppBusinessListVO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/17 12:52
 * @Version: 1.0
 */

@Data
public class AppBusinessListVO {

    private Integer id;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 创建人名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 单位名
     */
    private String companyName;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * 0. 回款 1.未回款
     */
    private Integer collectMoney;

}
