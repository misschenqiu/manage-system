package com.starda.managesystem.pojo.po.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.staff
 * @ClassName: StaffQueryPO
 * @Author: chenqiu
 * @Description: 查询 用户列表
 * @Date: 2021/8/28 11:25
 * @Version: 1.0
 */

@Data
public class StaffQueryPO {

    /**
     * 用户名
     */
    private String userName;

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
    private Integer currentPage;

    /**
     * 显示条数
     */
    private Integer pageSize;

    /**
     * 当前用户id
     */
    private Integer accountId;

}
