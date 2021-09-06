package com.starda.managesystem.service;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.CommonIdsPO;
import com.starda.managesystem.pojo.po.business.*;
import com.starda.managesystem.pojo.vo.business.BusinessInfoListVO;
import com.starda.managesystem.pojo.vo.business.BusinessInfoVO;
import com.starda.managesystem.pojo.vo.business.ConfirmTaskInfoListVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoLIstVO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IBusinessTaskService
 * @Author: chenqiu
 * @Description: 业务信息管理
 * @Date: 2021/9/1 15:31
 * @Version: 1.0
 */
public interface IBusinessTaskService {

    /**
     * 下发任务
     * @param user
     * @param taskInfo
     * @throws Exception
     */
    void insertTaskInfo(UserVO user, InsertTaskInfoPO taskInfo) throws Exception;

    /**
     * 删除任务
     * @param user
     * @param ids
     * @throws Exception
     */
    void removeTaskInfo(UserVO user, CommonIdsPO ids) throws Exception;

    /**
     * 任务列表信息
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result<TaskInfoLIstVO> getTaskInfoList(UserVO user, TaskInfoQueryPO po) throws Exception;

    /**
     * 流程确认信息
     * @param user
     * @param po
     * @throws Exception
     */
    List<ConfirmTaskInfoListVO> confirmTaskInfo(UserVO user, ConfirmTaskPO po) throws Exception;

    /****************** 业务信息 ********************/

    /**
     * 添加业务信息
     * @param user
     * @param business
     * @throws Exception
     */
    void insertBusinessInfo(UserVO user, InsertBusinessInfoPO business) throws Exception;

    /**
     * 删除业务
     * @param user
     * @param ids
     * @throws Exception
     */
    void removeBusinessInfo(UserVO user, CommonIdsPO ids) throws Exception;

    /**
     * 获取业务详情
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result<BusinessInfoListVO> getBusinessInfoList(UserVO user, QueryBusinessInfoPO po) throws Exception;

    /**
     * 修改业务信息
     * @param user
     * @param businessInfo
     * @throws Exception
     */
    void updateBusinessInfo(UserVO user, UpdateBusinessInfoPO businessInfo) throws Exception;

    /**
     * 获取业务详情信息
     * @param user
     * @param businessId
     * @return
     * @throws Exception
     */
    BusinessInfoVO getBusinessInfo(UserVO user, Integer businessId) throws Exception;

}
