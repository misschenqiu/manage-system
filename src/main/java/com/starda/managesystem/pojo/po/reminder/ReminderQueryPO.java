package com.starda.managesystem.pojo.po.reminder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.reminder
 * @ClassName: ReminderQueryPO
 * @Author: chenqiu
 * @Description: 查询消息列表
 * @Date: 2021/8/31 20:52
 * @Version: 1.0
 */

@Data
public class ReminderQueryPO {

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

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
