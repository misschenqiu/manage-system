package com.starda.managesystem.service;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.app.AppManageQueryBusinessPO;
import com.starda.managesystem.pojo.po.business.TaskInfoQueryPO;
import com.starda.managesystem.pojo.vo.app.AppBusinessInfoListVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoLIstVO;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IAppManageService
 * @Author: chenqiu
 * @Description: 管理员 app 列表
 * @Date: 2021/9/17 12:49
 * @Version: 1.0
 */
public interface IAppManageService {

    /**
     * 获取业务列表
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result getBusinessList(UserVO user, AppManageQueryBusinessPO po) throws Exception;

    /**
     * 任务列表信息
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result<AppBusinessInfoListVO> getAppTaskInfoList(UserVO user, TaskInfoQueryPO po) throws Exception;

}
