package com.starda.managesystem.pojo.po.reminder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.reminder
 * @ClassName: InsertReminderPO
 * @Author: chenqiu
 * @Description: 添加单位信息
 * @Date: 2021/8/31 20:43
 * @Version: 1.0
 */

@Data
public class InsertReminderPO {

    /**
     * 消息名称
     */
    private String reminderName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 是否开启 0 否  1.是
     */
    private Integer reminderOpen;

    /**
     * 重复提醒次数
     */
    private Integer againNumber;

    /**
     * 是否重复提醒 0.否 1.是
     */
    private Integer again;

    /**
     * 描述
     */
    private String remark;

    /**
     * 提醒时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @NotNull(message = "请选择提醒时间")
    private Date reminderTime;

    /**
     * 1.单次提醒，2.周期提醒
     */
    @NotNull(message = "请设置 单次或者周期")
    private Integer oneWeek;

    /**
     * 周期提醒类型 1.月，2.季，3.半年 4。全年
     */
    private Integer weekType;

    /**
     * 单位信息
     *
     */
    private List<Integer> companyIds;

    /**
     * 联系人电话
     */
    @NotBlank(message = "短信接收电话，不能为空")
    private String reminderPhone;

}
