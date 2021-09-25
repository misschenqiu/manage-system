package com.starda.managesystem.pojo.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.business
 * @ClassName: BusinessInfoListVO
 * @Author: chenqiu
 * @Description: 业务详情列表信息
 * @Date: 2021/9/4 13:33
 * @Version: 1.0
 */

@Data
public class BusinessInfoListVO {

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
     * 合作单位
     */
    private Integer companyId;

    /**
     * 单位名
     */
    private String companyName;

    /**
     * 0.未完成 1.完成
     */
    private Integer businessSucess;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * 0. 回款 1.未回款
     */
    private Integer collectMoney;

    /**
     * 保险单号
     */
    private String insuranceNumber;

    /**
     * 对应人
     */
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

    /**
     * 车牌号
     */
    private String carBrand;

    /**
     * 车架号
     */
    private String carShelf;

}
