package com.starda.managesystem.quertz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.quertz
 * @ClassName: CompanyMoneyTask
 * @Author: chenqiu
 * @Description: 单位回款信息 提醒
 * @Date: 2021/9/9 11:52
 * @Version: 1.0
 */
public class CompanyMoneyTask implements Job {

    /**
     * 执行 器
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
