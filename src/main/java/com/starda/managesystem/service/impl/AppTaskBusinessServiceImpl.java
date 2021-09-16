package com.starda.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.business.ManageBusinessInfoMapper;
import com.starda.managesystem.mapper.business.ManageBusinessRemarkMapper;
import com.starda.managesystem.mapper.system.SysStaffMapper;
import com.starda.managesystem.pojo.ManageBusiness;
import com.starda.managesystem.pojo.ManageBusinessInfo;
import com.starda.managesystem.pojo.ManageBusinessRemark;
import com.starda.managesystem.pojo.SysStaff;
import com.starda.managesystem.pojo.po.CommonParamPO;
import com.starda.managesystem.pojo.po.app.AppConfirmTaskPO;
import com.starda.managesystem.pojo.po.app.AppTaskQueryPO;
import com.starda.managesystem.pojo.po.business.ConfirmTaskPO;
import com.starda.managesystem.pojo.vo.app.AppTaskInfoListVO;
import com.starda.managesystem.pojo.vo.app.AppTaskInfoVO;
import com.starda.managesystem.pojo.vo.business.ManageBusinessInfoDTO;
import com.starda.managesystem.service.IAppTaskBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: AppTaskBusinessServiceImpl
 * @Author: chenqiu
 * @Description: 业务实现类（app端）
 * @Date: 2021/9/1 15:35
 * @Version: 1.0
 */

@Service
public class AppTaskBusinessServiceImpl extends ServiceImpl<ManageBusinessInfoMapper, ManageBusinessInfo> implements IAppTaskBusinessService {

    @Autowired
    private ManageBusinessRemarkMapper businessRemarkMapper;

    @Autowired
    private SysStaffMapper staffMapper;

    @Override
    public ManageBusinessInfo getBeforeTaskInfo(Integer taskId) throws Exception {

        ManageBusinessInfo manageBusinessInfo = this.baseMapper.selectByPrimaryKey(taskId);
        if(manageBusinessInfo.getLevel() == Constant.TaskBusinessType.ONE){
            return null;
        }
        // 查询前一个
        List<ManageBusinessInfo> manageBusinessInfos = this.baseMapper.selectList(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getBusinessId, manageBusinessInfo.getBusinessId())
                .eq(ManageBusinessInfo::getLevel, manageBusinessInfo.getLevel() - Constant.BaseNumberManage.ONE)
                .orderByDesc(ManageBusinessInfo::getCreateTime));
        if (null == manageBusinessInfos || manageBusinessInfos.isEmpty()){
            return null;
        }
        return manageBusinessInfos.get(Constant.BaseNumberManage.ZERO);
    }

    @Override
    public Result<AppTaskInfoListVO> getTaskInfoList(UserVO user, AppTaskQueryPO po) throws Exception {
        // 封装条件
        MPJLambdaWrapper<ManageBusinessInfo> queryWrapper = new MPJLambdaWrapper<ManageBusinessInfo>();
        queryWrapper.selectAll(ManageBusinessInfo.class)
                .leftJoin(ManageBusiness.class, ManageBusiness::getId, ManageBusinessInfo::getBusinessId);
        queryWrapper.eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE);
        queryWrapper.eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE);
        queryWrapper.eq(ManageBusinessInfo::getStaffId, user.getStaffId());
        queryWrapper.eq(ManageBusinessInfo::getConfirmIssue, Constant.ConfirmTaskType.ONE);
        queryWrapper.orderByDesc(ManageBusinessInfo::getCreateTime).orderByDesc(ManageBusinessInfo::getStaffSubmit);

        switch (po.getTaskType()){
            // 已完成的
            case Constant.AppQueryType.TWO:
                queryWrapper.eq(ManageBusinessInfo::getFinish, Constant.TaskBusinessType.THREE);
                IPage<AppTaskInfoListVO> pageInfo = this.baseMapper.selectJoinPage(new Page<AppTaskInfoListVO>(po.getCurrentPage(), po.getPageSize()), AppTaskInfoListVO.class, queryWrapper);
                return Result.ok().resultPage(pageInfo.getRecords(), pageInfo.getCurrent(), pageInfo.getSize(), pageInfo.getTotal());
            // 待完成的
            case Constant.AppQueryType.ONE:
                queryWrapper.and(param->{
                    param.eq(ManageBusinessInfo::getFinish, Constant.TaskBusinessType.ZERO)
                            .or()
                            .eq(ManageBusinessInfo::getFinish, Constant.TaskBusinessType.FIVE);
                });
                IPage<AppTaskInfoListVO> pageTask = this.baseMapper.selectJoinPage(new Page<AppTaskInfoListVO>(po.getCurrentPage(), po.getPageSize()), AppTaskInfoListVO.class, queryWrapper);
                return Result.ok().resultPage(pageTask.getRecords(), pageTask.getCurrent(), pageTask.getSize(), pageTask.getTotal());
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmTaskInfo(UserVO user, AppConfirmTaskPO po) throws Exception {
        // 填充数据
        ManageBusinessRemark remark = new ManageBusinessRemark();
        remark.setRemark(po.getRemark());
        remark.setRemarkImg(po.getTaskImg());
        remark.setBusinessInfoId(po.getBusinessId());
        remark.setCreateAccountId(user.getId());
        remark.setCreateUserName(user.getStaffName());
        remark.setRemarkType(Constant.PeopleType.STAFF);
        // 添加 信息列表
        ManageBusinessInfo beforeTaskInfo = this.getBeforeTaskInfo(po.getBusinessId());
        if (beforeTaskInfo != null) {
            if (beforeTaskInfo.getConfirmIssue() == Constant.ConfirmTaskType.ZERO || !beforeTaskInfo.getFinish().equals(Constant.TaskBusinessType.THREE)) {
                throw new ManageStarException("存在当前任务之前的任务未结束，不可操作");
            }
        }
        // 添加数据
        this.businessRemarkMapper.insertSelective(remark);
        // 处理数据
        ManageBusinessInfo taskInfo = new ManageBusinessInfo();
        taskInfo.setId(po.getBusinessId());
        switch (po.getConfirmType()){
            // 完成
            case Constant.TaskBusinessType.THREE:
                taskInfo.setStaffSubmit(Constant.StaffSubmitType.SUBMIT_YES);
                this.updateById(taskInfo);
                break;
            // 退回当前任务 当前任务结束，新开一个任务
            case Constant.TaskBusinessType.FOUR:
                taskInfo.setFinish(Constant.TaskBusinessType.FOUR);
                taskInfo.setStaffSubmit(Constant.StaffSubmitType.SUBMIT_YES);
                this.updateById(taskInfo);
                // 填充新数据
                ManageBusinessInfo businessInfo = this.getById(po.getBusinessId());
                businessInfo.setId(null);
                businessInfo.setCreateTime(new Date());
                businessInfo.setConfirmIssue(Constant.ConfirmTaskType.ZERO);
                businessInfo.setFinish(Constant.TaskBusinessType.FOUR);
                businessInfo.setPid(po.getBusinessId());
                this.save(businessInfo);
                break;
        }

    }

    @Override
    public AppTaskInfoVO getTaskInfo(UserVO user, ConfirmTaskPO po) throws Exception {
        return null;
    }

    @Override
    public List<ManageBusinessInfoDTO> getManageBusinessInfoList(MPJLambdaWrapper<ManageBusinessInfo> wrapper) throws Exception {
        List<ManageBusinessInfoDTO> manageBusinessInfoDTOS = this.baseMapper.selectJoinList(ManageBusinessInfoDTO.class, wrapper);
        return manageBusinessInfoDTOS;
    }

    @Override
    public void confirmBusinessInfo(UserVO user, Integer businessId) throws Exception {
        ManageBusinessInfo businessInfo = new ManageBusinessInfo();
        businessInfo.setId(businessId);
        businessInfo.setStaffConfirm(Constant.BaseNumberManage.ONE);
        this.baseMapper.updateById(businessInfo);
    }

    @Override
    public void phoneSerial(UserVO user, CommonParamPO param) throws Exception{

        SysStaff sysStaff = new SysStaff();
        sysStaff.setId(user.getStaffId());
        sysStaff.setPhoneSerial(param.getParam());

        this.staffMapper.updateById(sysStaff);

    }

}
