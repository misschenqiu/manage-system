package com.starda.managesystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.mapper.business.ManageMessageLogMapper;
import com.starda.managesystem.mapper.business.ManageReCompMapper;
import com.starda.managesystem.pojo.ManageBusiness;
import com.starda.managesystem.pojo.ManageMessageLog;
import com.starda.managesystem.pojo.ManageReComp;
import com.starda.managesystem.pojo.ManageReminder;
import com.starda.managesystem.quertz.CompanyMoneyTask;
import com.starda.managesystem.service.IScheduledTimeService;
import com.starda.managesystem.service.ITaskJobService;
import com.starda.managesystem.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: TaskJobServiceImpl
 * @Author: chenqiu
 * @Description: 定时任务信息
 * @Date: 2021/9/11 19:55
 * @Version: 1.0
 */

@Service
@Log4j2
public class TaskJobServiceImpl implements ITaskJobService {

    @Autowired
    private IScheduledTimeService reminderService;

    @Autowired
    private ManageReCompMapper manageReCompMapper;

    @Autowired
    private ManageMessageLogMapper logMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void taskJob() throws Exception {

        // 1.获取开启任务 处理提醒数据
        List<ManageReminder> reminders = this.reminderService.list(new LambdaQueryWrapper<ManageReminder>()
                .eq(ManageReminder::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageReminder::getReminderOpen, Constant.BaseNumberManage.ONE)
                .between(ManageReminder::getReminderTime, DateUtil.getStartDate(new Date()), DateUtil.getEndDate(new Date())));
        if (null == reminders || reminders.isEmpty()) {
            return;
        }
        List<Integer> reminderIds = reminders.stream().map(data -> data.getId()).collect(Collectors.toList());
        List<ManageReComp> companyIds = this.manageReCompMapper.selectJoinList(ManageReComp.class, new MPJLambdaWrapper<ManageReComp>()
                .selectAll(ManageReComp.class)
                .leftJoin(ManageBusiness.class, ManageBusiness::getCompanyId, ManageReComp::getCompanyId)
                .eq(ManageBusiness::getBusinessSucess, Constant.ConfirmTaskType.ONE)
                .eq(ManageBusiness::getCollectMoney, Constant.BaseNumberManage.ZERO)
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .in(ManageReComp::getReminderId, reminderIds));
        if (null == companyIds || companyIds.isEmpty()) {
            return;
        }

        // 2.获取提信消息
        Map<Integer, List<ManageReComp>> integerListMap = companyIds.stream().collect(Collectors.groupingBy(ManageReComp::getReminderId));
        Set<Integer> reminderIdList = integerListMap.keySet();
        List<ManageReminder> reminderList = reminders.stream().filter(reminderId -> reminderIdList.contains(reminderId)).collect(Collectors.toList());

        // 3 获取已经提醒的数据
        reminderList.stream().forEach(reminder -> {
            // 4.调用定时器
            try {
                taskJod(reminder);
                // 5.保存日志
                insertReminderLog(reminder);
            } catch (Exception e) {
                log.error("调用定时器出现异常" + e.getMessage());
                return;
            }
        });

        // 6. 修改下次发送时间
        reminderList.forEach(reminder -> {
            // 单次发送 并且重复发送的
        });

    }

    /**
     * 调用定时器
     *
     * @param reminderInfo
     * @throws Exception
     */
    public void taskJod(ManageReminder reminderInfo) throws Exception {
        // 1.实例化调度器
        JobDetail jobDetail = JobBuilder.newJob(CompanyMoneyTask.class)
                .withDescription("定时调用发送短信信息")
                .withIdentity("reminderInfo", JSONObject.toJSONString(reminderInfo))
                .build();

        // 2.实例化触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("发送消息触发器")
                .startAt(reminderInfo.getReminderTime())
                .withSchedule(SimpleScheduleBuilder
                        .repeatSecondlyForTotalCount(10, 5)
                        // 重复执行次数
                        .withRepeatCount(0))
                .build();

        // 3.创建一个调度器，也就是一个Quartz容器
        //声明一个scheduler的工厂schedulerFactory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //通过schedulerFactory来实例化一个Scheduler
        Scheduler scheduler = schedulerFactory.getScheduler();
        //将Job和Trigger注册到scheduler容器中
        scheduler.scheduleJob(jobDetail, trigger);

        //启动容器
        log.info("JOB开始启动");
        scheduler.start();

    }


    @Override
    public void insertReminderLog(ManageReminder reminder) throws Exception {

        ManageMessageLog reminderLog = new ManageMessageLog();
        reminderLog.setCreateTime(reminder.getReminderTime());
        reminderLog.setContent(reminder.getContent());
        reminderLog.setPhone(reminder.getReminderPhone());
        reminderLog.setReminderId(reminder.getId());
        reminderLog.setMessageSeccuss(Constant.BaseNumberManage.ONE);
        reminderLog.setNumber(Constant.BaseNumberManage.ONE);

        this.logMapper.insert(reminderLog);
        log.info("添加日志成功");
    }

}
