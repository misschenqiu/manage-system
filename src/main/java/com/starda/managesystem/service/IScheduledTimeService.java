package com.starda.managesystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.ManageReminder;
import com.starda.managesystem.pojo.po.RemoveIdsPO;
import com.starda.managesystem.pojo.po.reminder.InsertReminderPO;
import com.starda.managesystem.pojo.po.reminder.ReminderQueryPO;
import com.starda.managesystem.pojo.po.reminder.ReminderUpdatePO;
import com.starda.managesystem.pojo.vo.ReminderListVO;


/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IScheduledTimeService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/31 0:21
 * @Version: 1.0
 */
public interface IScheduledTimeService extends IService<ManageReminder> {

    /**
     * 添加提醒消息
     * @param user
     * @param reminder
     * @throws Exception
     */
    void insertReminderInfo(UserVO user, InsertReminderPO reminder) throws Exception;

    /**
     * 删除 或关闭消息
     * @param user
     * @param po
     * @throws Exception
     */
    void removeReminderInfo(UserVO user, RemoveIdsPO po) throws Exception;

    /**
     * 获取到消息列表
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result<ReminderListVO> getReminderList(UserVO user, ReminderQueryPO po) throws Exception;

    /**
     * 修改
     * @param user
     * @param po
     * @throws Exception
     */
    void updateReminderInfo(UserVO user, ReminderUpdatePO po) throws Exception;

    /**
     * 消息列表
     * @param user
     * @param reminderId
     * @return
     * @throws Exception
     */
    ReminderListVO getReminderInfo(UserVO user, Integer reminderId) throws Exception;

}
