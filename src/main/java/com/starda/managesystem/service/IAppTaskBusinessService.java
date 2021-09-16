package com.starda.managesystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.ManageBusinessInfo;
import com.starda.managesystem.pojo.po.CommonParamPO;
import com.starda.managesystem.pojo.po.app.AppConfirmTaskPO;
import com.starda.managesystem.pojo.po.app.AppTaskQueryPO;
import com.starda.managesystem.pojo.po.business.ConfirmTaskPO;
import com.starda.managesystem.pojo.vo.app.AppTaskInfoListVO;
import com.starda.managesystem.pojo.vo.app.AppTaskInfoVO;
import com.starda.managesystem.pojo.vo.business.ManageBusinessInfoDTO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IAppTaskBusinessService
 * @Author: chenqiu
 * @Description: 业务app端信息
 * @Date: 2021/9/1 15:34
 * @Version: 1.0
 */
public interface IAppTaskBusinessService extends IService<ManageBusinessInfo> {

    /**
     * 获取到当前任务的前一个任务
     * @param taskId 任务id
     * @return
     * @throws Exception
     */
    ManageBusinessInfo getBeforeTaskInfo(Integer taskId) throws Exception;

    /**
     * app员工获取到自己任务信息
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result<AppTaskInfoListVO> getTaskInfoList(UserVO user, AppTaskQueryPO po) throws Exception;

    /**
     * 确认任务
     * @param user
     * @param po
     * @throws Exception
     */
    void confirmTaskInfo(UserVO user, AppConfirmTaskPO po) throws Exception;

    /**
     * app 任务详情
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    AppTaskInfoVO getTaskInfo(UserVO user, ConfirmTaskPO po) throws Exception;

    /**
     * 获取到详情
     * @param wrapper
     * @return
     * @throws Exception
     */
    List<ManageBusinessInfoDTO> getManageBusinessInfoList(MPJLambdaWrapper<ManageBusinessInfo> wrapper) throws Exception;

    /**
     * 员工接收任务
     * @param user
     * @param businessId
     * @throws Exception
     */
    void confirmBusinessInfo(UserVO user, Integer businessId) throws Exception;

    /**
     * 添加手机序列号
     * @param user
     * @param param
     * @throws Exception
     */
    void phoneSerial(UserVO user, CommonParamPO param) throws Exception;

}
