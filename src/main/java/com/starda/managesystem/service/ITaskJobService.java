package com.starda.managesystem.service;

import com.starda.managesystem.pojo.ManageReminder;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: ITaskJobService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/11 19:55
 * @Version: 1.0
 */
public interface ITaskJobService {

    /**
     * 定时器处理发送短信信息
     * @throws Exception
     */
    void taskJob() throws Exception;

    /**
     * 保存日志
     * @param reminder
     * @throws Exception
     */
    void insertReminderLog(ManageReminder reminder) throws Exception;

}
