package com.starda.managesystem.pojo.vo.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.company
 * @ClassName: CompanyListVO
 * @Author: chenqiu
 * @Description: 单位显示列表
 * @Date: 2021/8/30 20:49
 * @Version: 1.0
 */

@Data
public class CompanyListVO {

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
     * 描述
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 创建者用户名
     */
    private String userName;

    /**
     * 联系电话
     */
    private String companyPhone;

    /**
     * 联系人名
     */
    private String companyUser;

}
