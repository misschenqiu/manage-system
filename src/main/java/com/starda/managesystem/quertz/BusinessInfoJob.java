package com.starda.managesystem.quertz;

import com.starda.managesystem.service.IAppManageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.quertz
 * @ClassName: BusinessInfoJob
 * @Author: chenqiu
 * @Description: 判断员工是否接收任务
 * @Date: 2021/9/22 22:51
 * @Version: 1.0
 */
@Configuration
@Log4j2
public class BusinessInfoJob {

    @Autowired
    private IAppManageService appManageService;

    /**
     * 重复判断 员工是否接收任务 每小时执行一次
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void againTaskStaff(){
        try {
            appManageService.getTaskInfoList();
            log.info("调用成功");
        } catch (Exception e) {
            log.info("调用失败" + e.getMessage());
        }
    }

}
