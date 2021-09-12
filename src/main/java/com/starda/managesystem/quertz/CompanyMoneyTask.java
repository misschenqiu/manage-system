package com.starda.managesystem.quertz;

import com.alibaba.fastjson.JSONObject;
import com.starda.managesystem.pojo.ManageReminder;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;

import java.time.LocalDateTime;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.quertz
 * @ClassName: CompanyMoneyTask
 * @Author: chenqiu
 * @Description: 单位回款信息 提醒
 * @Date: 2021/9/9 11:52
 * @Version: 1.0
 *
 * // 获取Scheduler
 *         Scheduler scheduler = context.getScheduler();
 *         // 获取Trigger
 *         Trigger trigger = context.getTrigger();
 *         // 通过Trigger获取JobDataMap参数
 *         JobDataMap triggerJobData = trigger.getJobDataMap();
 *         // 获取JobDetail
 *         JobDetail jobDetail = context.getJobDetail();
 *         // 通过JobDetail获取JobDetail参数
 *         JobDataMap jobDetailData = jobDetail.getJobDataMap();
 *         // 合并获取JobDataMap，存在键值相同的，取Trigger JobDataMap的值
 *         JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
 *
 */

@Log4j2
public class CompanyMoneyTask implements Job {

    /**
     * 执行 器
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("开始调用任务信息 =>" + LocalDateTime.now().toString());

        // 获取发送信息
        String reminderInfo = context.getJobDetail().getJobDataMap().get("reminderInfo") + "";
        ManageReminder reminder = JSONObject.parseObject(reminderInfo, ManageReminder.class);

        // 发送消息

        log.info("结束调用任务信息 =>" + LocalDateTime.now().toString());
    }

}
