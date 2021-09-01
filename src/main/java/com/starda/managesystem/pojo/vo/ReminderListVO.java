package com.starda.managesystem.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starda.managesystem.pojo.vo.company.CompanySmilVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo
 * @ClassName: ReminderListVO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/31 20:54
 * @Version: 1.0
 */

@Data
public class ReminderListVO {

    private Integer id;

    /**
     * 消息名称
     */
    private String reminderName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 是否开启 0 否  1.是
     */
    private Integer reminderOpen;

    /**
     * 重复提醒次数
     */
    private Integer againNumber;

    /**
     * 创建人信息
     */
    private String createUserName;

    /**
     * 是否重复提醒 0.否 1.是
     */
    private Integer again;

    /**
     * 描述
     */
    private String remark;

    /**
     * 合作单位信息
     */
    private List<CompanySmilVO> companyName;

}
