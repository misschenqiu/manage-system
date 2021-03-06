package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.business.ManageBusinessMapper;
import com.starda.managesystem.mapper.business.ManageBusinessRemarkMapper;
import com.starda.managesystem.pojo.ManageBusiness;
import com.starda.managesystem.pojo.ManageBusinessInfo;
import com.starda.managesystem.pojo.ManageBusinessRemark;
import com.starda.managesystem.pojo.SysStaff;
import com.starda.managesystem.pojo.enums.FinishTimeEnums;
import com.starda.managesystem.pojo.po.CommonIdsPO;
import com.starda.managesystem.pojo.po.CommonUpdateIdPO;
import com.starda.managesystem.pojo.po.business.*;
import com.starda.managesystem.pojo.vo.business.*;
import com.starda.managesystem.service.IAppTaskBusinessService;
import com.starda.managesystem.service.IBusinessTaskService;
import com.starda.managesystem.util.getui.GeTuiUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: BusinessTaskServiceImpl
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/1 15:32
 * @Version: 1.0
 */

@Service
@Log4j2
public class BusinessTaskServiceImpl extends ServiceImpl<ManageBusinessMapper, ManageBusiness> implements IBusinessTaskService {

    @Autowired
    private IAppTaskBusinessService taskBusinessService;

    @Autowired
    private ManageBusinessRemarkMapper businessRemarkMapper;

    @Override
    public void insertTaskInfo(UserVO user, InsertTaskInfoPO taskInfo) throws Exception {

        // ??????????????????????????????
        ManageBusiness manageBusiness = this.getById(taskInfo.getBusinessId());
        if(manageBusiness.getBusinessSucess().equals(Constant.BaseNumberManage.ONE)){
            throw new ManageStarException("??????????????????????????????????????????");
        }

        //1. ???????????????????????????
        List<ManageBusinessInfo> list = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getBusinessId, taskInfo.getBusinessId())
                .orderByDesc(ManageBusinessInfo::getLevel)
                .orderByDesc(ManageBusinessInfo::getCreateTime));

        /**
         * ????????????
         */
        ManageBusinessInfo manageBusinessInfo = BeanUtil.toBean(taskInfo, ManageBusinessInfo.class);
        // ?????? ??????
        if (null == list || list.isEmpty()) {
            manageBusinessInfo.setLevel(Constant.BaseNumberManage.ONE);
        } else {
            ManageBusinessInfo businessInfo = list.get(Constant.BaseNumberManage.ZERO);
            manageBusinessInfo.setLevel(businessInfo.getLevel() + Constant.BaseNumberManage.ONE);
        }
        // ??????
        manageBusinessInfo.setStatus(Constant.BaseNumberManage.ONE);
        manageBusinessInfo.setFinish(Constant.BaseNumberManage.ZERO);
        // ???????????????
        manageBusinessInfo.setCreateAccountId(user.getId());
        manageBusinessInfo.setCreateTime(new Date());
        manageBusinessInfo.setCreateUser(user.getStaffName());
        // ????????????
        this.taskBusinessService.save(manageBusinessInfo);
        log.info("??????????????????");

    }

    @Override
    public void removeTaskInfo(UserVO user, CommonIdsPO ids) throws Exception {
        // ???????????????????????????
        boolean flash = false;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = true;
        }
        List<ManageBusinessInfo> list = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(flash, ManageBusinessInfo::getCreateAccountId, user.getId())
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getFinish, Constant.TaskBusinessType.ZERO)
                .in(ManageBusinessInfo::getId, ids.getIds()));
        if(null == list || list.isEmpty()){
            throw new ManageStarException("??????????????????????????????");
        }

        // ????????????
        list.stream().forEach(param -> {
            param.setStatus(Constant.BaseNumberManage.ZERO);
        });
        this.taskBusinessService.updateBatchById(list);
        log.info("????????????");
    }

    @Override
    public TaskInfoVO getTaskInfo(UserVO user, ConfirmTaskPO businessId) throws Exception {
        // ?????????????????????
        ManageBusinessInfo manageBusinessInfo = this.taskBusinessService.getOne(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getId, businessId.getBusinessId()));
        if (manageBusinessInfo == null) {
            return null;
        }
        TaskInfoVO taskInfoVO = BeanUtil.toBean(manageBusinessInfo, TaskInfoVO.class);
        // ??????????????? ?????? ??????
        MPJLambdaWrapper<ManageBusinessRemark> manageWrapper = new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .eq(ManageBusinessRemark::getRemarkType, Constant.PeopleType.MANAGE);
        if (manageBusinessInfo.getFinish().equals(Constant.TaskBusinessType.FIVE) && manageBusinessInfo.getPid() != null) {
            manageWrapper.eq(ManageBusinessRemark::getBusinessInfoId, manageBusinessInfo.getPid());
        } else {
            manageWrapper.eq(ManageBusinessRemark::getBusinessInfoId, manageBusinessInfo.getId());
        }
        List<TaskInfoRemarkVO> manageRemark = this.businessRemarkMapper.selectJoinList(TaskInfoRemarkVO.class, manageWrapper);
        if (null != manageRemark && !manageRemark.isEmpty()) {
            taskInfoVO.setManageRemarkVO(manageRemark.get(Constant.BaseNumberManage.ZERO));
        }

        // ????????????????????????
        MPJLambdaWrapper<ManageBusinessRemark> wrapper = new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .selectAs(ManageBusinessRemark::getCreateUserName, TaskStaffInfoVO::getStaffName)
                .selectAs(SysStaff::getHead_img, TaskStaffInfoVO::getStaffHeadImg)
                .leftJoin(SysStaff.class, SysStaff::getId, ManageBusinessRemark::getCreateAccountId)
                .eq(ManageBusinessRemark::getRemarkType, Constant.PeopleType.STAFF);
        if (manageBusinessInfo.getFinish().equals(Constant.TaskBusinessType.FOUR) && manageBusinessInfo.getPid() != null) {
            wrapper.eq(ManageBusinessRemark::getBusinessInfoId, manageBusinessInfo.getPid());
        } else {
            wrapper.eq(ManageBusinessRemark::getBusinessInfoId, manageBusinessInfo.getId());
        }
        // ??????????????????
        List<TaskStaffInfoVO> taskStaffInfoVOList = this.businessRemarkMapper.selectJoinList(TaskStaffInfoVO.class, wrapper);
        if (null == taskStaffInfoVOList || taskStaffInfoVOList.isEmpty()) {
            return taskInfoVO;
        }
        taskInfoVO.setStaffInfoVO(taskStaffInfoVOList.get(Constant.BaseNumberManage.ZERO));
        return taskInfoVO;
    }

    @Override
    public void updateTaskInfo(UserVO user, UpdateTaskInfoPO taskInfo) throws Exception {
        // ????????????????????????
        ManageBusinessInfo taskBusinessInfo = this.taskBusinessService.getOne(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getId, taskInfo.getId())
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE));
        if (taskBusinessInfo.getFinish().equals(Constant.TaskBusinessType.THREE)
                || taskBusinessInfo.getStaffSubmit().equals(Constant.StaffSubmitType.SUBMIT_YES)) {
            throw new ManageStarException("????????????????????????????????????");
        }
        ManageBusinessInfo manageBusinessInfo = BeanUtil.toBean(taskInfo, ManageBusinessInfo.class);
        manageBusinessInfo.setUpdateTime(new Date());
        manageBusinessInfo.setUpdateAccount(user.getId());

        this.taskBusinessService.updateById(manageBusinessInfo);
    }

    @Override
    public Result<TaskInfoLIstVO> getTaskInfoList(UserVO user, TaskInfoQueryPO po) throws Exception {
        //1. ?????????????????????
        Page<ManageBusinessInfo> page = this.taskBusinessService.page(new Page<ManageBusinessInfo>(po.getCurrentPage(), po.getPageSize()),
                new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getBusinessId, po.getBusinessId())
                .orderByAsc(ManageBusinessInfo::getLevel));
        // 2.???????????????????????????
        List<TaskInfoLIstVO> taskInfoLIstVOS = BeanUtil.copyToList(page.getRecords(), TaskInfoLIstVO.class);
        List<Integer> businessInfoIds = taskInfoLIstVOS.stream().map(taskInfo -> taskInfo.getId()).collect(Collectors.toList());
        List<TaskInfoRemarkVO> taskInfoRemarkVOS = this.businessRemarkMapper.selectJoinList(TaskInfoRemarkVO.class, new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .eq(ManageBusinessRemark::getRemarkType, Constant.PeopleType.MANAGE)
                .in(businessInfoIds != null && !businessInfoIds.isEmpty(), ManageBusinessRemark::getBusinessInfoId, businessInfoIds));

        // ??????????????????
        List<TaskStaffInfoVO> taskStaffInfoVOList = this.businessRemarkMapper.selectJoinList(TaskStaffInfoVO.class, new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .selectAs(ManageBusinessRemark::getCreateUserName, TaskStaffInfoVO::getStaffName)
                .selectAs(SysStaff::getHead_img, TaskStaffInfoVO::getStaffHeadImg)
                .leftJoin(SysStaff.class, SysStaff::getId, ManageBusinessRemark::getCreateAccountId)
                .eq(ManageBusinessRemark::getRemarkType, Constant.PeopleType.STAFF)
                .in(businessInfoIds != null && !businessInfoIds.isEmpty(), ManageBusinessRemark::getBusinessInfoId, businessInfoIds));

        // ????????????
        Map<Integer, List<TaskStaffInfoVO>> staffMap = taskStaffInfoVOList.stream().collect(Collectors.groupingBy(TaskStaffInfoVO::getBusinessInfoId));
        Map<Integer, List<TaskInfoRemarkVO>> integerListMap = taskInfoRemarkVOS.stream().collect(Collectors.groupingBy(TaskInfoRemarkVO::getBusinessInfoId));

        taskInfoLIstVOS.stream().forEach(task -> {
            List<TaskInfoRemarkVO> taskInfoRemarkList = integerListMap.get(task.getId());
            if (null != taskInfoRemarkList && !taskInfoRemarkList.isEmpty()) {
                task.setManageRemarkVO(taskInfoRemarkList.get(Constant.BaseNumberManage.ZERO));
            }
            // ??????
            List<TaskStaffInfoVO> staffList = staffMap.get(task.getId());
            if (null != staffList && !staffList.isEmpty()) {
                task.setStaffInfoVO(staffList.get(Constant.BaseNumberManage.ZERO));
            }
        });
        return Result.ok().resultPage(taskInfoLIstVOS, page.getCurrent(), page.getSize(), page.getTotal());
    }

    @Override
    public void confirmIssueTask(UserVO user, CommonUpdateIdPO po) throws Exception {
        // ??????????????????????????????
        ManageBusiness business = this.getBaseMapper().selectJoinOne(ManageBusiness.class, new MPJLambdaWrapper<ManageBusiness>()
                .selectAll(ManageBusiness.class)
                .rightJoin(ManageBusinessInfo.class, ManageBusinessInfo::getBusinessId, ManageBusiness::getId)
                .eq(ManageBusinessInfo::getId, po.getId()));
        if(business.getBusinessSucess().equals(Constant.BaseNumberManage.ONE)){
            throw new ManageStarException("????????????????????????????????????");
        }

        // ???????????????????????????
        ManageBusinessInfo businessInfo = this.taskBusinessService.getById(po.getId());
        List<ManageBusinessInfo> businessInfos = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getBusinessId, businessInfo.getBusinessId())
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getLevel, businessInfo.getLevel() - Constant.BaseNumberManage.ONE)
                .orderByDesc(ManageBusinessInfo::getCreateTime));
        if(null != businessInfos && !businessInfos.isEmpty()){
            if(!businessInfos.get(Constant.BaseNumberManage.ZERO).getFinish().equals(Constant.TaskBusinessType.THREE)){
               throw new ManageStarException("????????????????????????????????????????????????");
            }
        }

        // ????????????
        ManageBusinessInfo taskInfo = new ManageBusinessInfo();
        taskInfo.setId(po.getId());
        taskInfo.setConfirmIssue(Constant.ConfirmTaskType.ONE);
        this.taskBusinessService.updateById(taskInfo);

        // ??????????????????
        List<String> phoneSeres = new ArrayList<String>();
        phoneSeres.add(user.getPhoneSerial());
        GeTuiUtil.messageInfoGetui(Constant.ResultCodeMessage.GE_TUI_TITLE, businessInfo.getTaskName(), phoneSeres);
    }

    @Override
    public List<ConfirmTaskInfoListVO> confirmTaskInfoList(UserVO user, ConfirmTaskPO po) throws Exception {

        //1. ????????????????????????????????????
        List<ManageBusinessInfoDTO> businessInfoList = this.taskBusinessService.getManageBusinessInfoList(new MPJLambdaWrapper<ManageBusinessInfo>()
                .selectAll(ManageBusinessInfo.class)
                .selectAs(SysStaff::getHead_img, ManageBusinessInfoDTO::getHeadImg)
                .leftJoin(SysStaff.class, SysStaff::getId, ManageBusinessInfo::getStaffId)
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getBusinessId, po.getBusinessId())
                .orderByAsc(ManageBusinessInfo::getLevel)
                .orderByDesc(ManageBusinessInfo::getCreateTime));

        //2. ??????????????????????????????????????????
        List<TaskInfoLIstVO> taskInfoLIstVOS = BeanUtil.copyToList(businessInfoList, TaskInfoLIstVO.class);
        List<Integer> businessInfoIds = taskInfoLIstVOS.stream().map(taskInfo -> taskInfo.getId()).collect(Collectors.toList());
        List<TaskInfoRemarkVO> taskInfoRemarkVOS = this.businessRemarkMapper.selectJoinList(TaskInfoRemarkVO.class, new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .eq(ManageBusinessRemark::getRemarkType, Constant.BaseNumberManage.TWO)
                .in(businessInfoIds != null && !businessInfoIds.isEmpty(), ManageBusinessRemark::getBusinessInfoId, businessInfoIds));

        //3. ?????????????????????
        List<TaskStaffInfoVO> taskStaffInfoVOList = this.businessRemarkMapper.selectJoinList(TaskStaffInfoVO.class, new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .selectAs(ManageBusinessRemark::getCreateUserName, TaskStaffInfoVO::getStaffName)
                .selectAs(SysStaff::getHead_img, TaskStaffInfoVO::getStaffHeadImg)
                .leftJoin(SysStaff.class, SysStaff::getId, ManageBusinessRemark::getCreateAccountId)
                .eq(ManageBusinessRemark::getRemarkType, Constant.BaseNumberManage.ONE)
                .in(businessInfoIds != null && !businessInfoIds.isEmpty(), ManageBusinessRemark::getBusinessInfoId, businessInfoIds));

        //4. ????????????
        Map<Integer, List<TaskInfoRemarkVO>> manageMap = taskInfoRemarkVOS.stream().collect(Collectors.groupingBy(TaskInfoRemarkVO::getBusinessInfoId));
        Map<Integer, List<TaskStaffInfoVO>> staffMap = taskStaffInfoVOList.stream().collect(Collectors.groupingBy(TaskStaffInfoVO::getBusinessInfoId));

        List<ConfirmTaskInfoListVO> confirmTaskInfoListVOS = BeanUtil.copyToList(businessInfoList, ConfirmTaskInfoListVO.class);

        confirmTaskInfoListVOS.stream().forEach(confirm -> {
            // ?????????
            List<TaskInfoRemarkVO> taskInfoRemarkList = manageMap.get(confirm.getId());
            if (null != taskInfoRemarkList && !taskInfoRemarkList.isEmpty()) {
                confirm.setManageRemarkVO(taskInfoRemarkList.get(Constant.BaseNumberManage.ZERO));
            }
            // ??????
            List<TaskStaffInfoVO> staffList = staffMap.get(confirm.getId());
            if (null != staffList && !staffList.isEmpty()) {
                confirm.setStaffInfoVO(staffList.get(Constant.BaseNumberManage.ZERO));
            }
            // ????????????
            confirm.setFinishType(FinishTimeEnums.getMessage(confirm.getFinish()));
        });

        return confirmTaskInfoListVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmTaskInfo(UserVO user, ConfirmTaskPO confirm) throws Exception {
        // ????????????
        ManageBusinessRemark remark = new ManageBusinessRemark();
        remark.setRemark(confirm.getRemark());
        remark.setBusinessInfoId(confirm.getBusinessId());
        remark.setCreateAccountId(user.getId());
        remark.setCreateUserName(user.getStaffName());
        remark.setRemarkType(Constant.PeopleType.MANAGE);
        // ?????? ????????????
        this.insertManageRemarkInfo(user, remark);
        // ??????????????????
        ManageBusinessInfo taskInfo = new ManageBusinessInfo();
        taskInfo.setId(confirm.getBusinessId());
        switch (confirm.getType()) {
            // ??????
            case Constant.TaskBusinessType.THREE:
                taskInfo.setFinish(Constant.TaskBusinessType.THREE);
                taskInfo.setFinishTime(new Date());
                this.taskBusinessService.updateById(taskInfo);
                break;
            // ?????? ???????????????????????? ????????????????????????????????????????????????
            case Constant.TaskBusinessType.FIVE:
                // ??????????????????
                ManageBusinessInfo businessInfo = this.taskBusinessService.getById(confirm.getBusinessId());
                // 1.????????????????????????
                taskInfo.setFinish(Constant.TaskBusinessType.FIVE);
                this.taskBusinessService.updateById(taskInfo);
                // ???????????????
                businessInfo.setId(null);
                businessInfo.setConfirmIssue(Constant.ConfirmTaskType.ONE);
                businessInfo.setFinish(Constant.TaskBusinessType.FIVE);
                businessInfo.setCreateTime(new Date());
                businessInfo.setPid(confirm.getBusinessId());
                businessInfo.setCreateAccountId(user.getId());
                businessInfo.setCreateUser(user.getStaffName());
                businessInfo.setStaffSubmit(Constant.StaffSubmitType.SUBMIT_NO);
                this.taskBusinessService.save(businessInfo);
                break;
        }

    }

    @Override
    public void confirmTaskSuccessOrMoney(UserVO user, ConfirmTaskPO confirm) throws Exception {
        // ?????????????????????????????????????????????????????????
        List<ManageBusinessInfo> businessInfos = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getFinish, Constant.TaskBusinessType.ZERO)
                .eq(ManageBusinessInfo::getBusinessId, confirm.getBusinessId())
        );

        if(null != businessInfos && !businessInfos.isEmpty()){
            throw new ManageStarException("????????????????????????????????????????????????");
        }
        ManageBusiness business = new ManageBusiness();
        business.setId(confirm.getBusinessId());
        switch (confirm.getType()){
            // ??????
            case Constant.BusinessType.ONE:
                business.setCollectMoney(Constant.BaseNumberManage.ONE);
                break;
            // ??????
            case Constant.BusinessType.TWO:
                List<ManageBusinessInfo> manageBusinessInfos = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                        .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                        .eq(ManageBusinessInfo::getBusinessId, confirm.getBusinessId())
                        .orderByDesc(ManageBusinessInfo::getLevel)
                        .orderByDesc(ManageBusinessInfo::getCreateTime));
                if(null == manageBusinessInfos || manageBusinessInfos.isEmpty()){
                    return;
                }
                business.setEndTime(manageBusinessInfos.get(Constant.BaseNumberManage.ZERO).getFinishTime());
                business.setBusinessSucess(Constant.BaseNumberManage.ONE);

                break;
        }

        this.updateById(business);

    }

    /*****         ????????????          *****/

    @Override
    public void insertBusinessInfo(UserVO user, InsertBusinessInfoPO business) throws Exception {

        ManageBusiness manageBusiness = BeanUtil.toBean(business, ManageBusiness.class);
        // ????????? ????????? ????????? ???????????? ?????????
        manageBusiness.setBusinessSucess(Constant.BaseNumberManage.ZERO);
        manageBusiness.setCollectMoney(Constant.BaseNumberManage.ZERO);
        manageBusiness.setCreateTime(new Date());
        manageBusiness.setCreateUserName(user.getStaffName());
        manageBusiness.setCreateAccountId(user.getId());
        manageBusiness.setStatus(Constant.BaseNumberManage.ONE);

        this.getBaseMapper().insertSelective(manageBusiness);
    }

    @Override
    public void removeBusinessInfo(UserVO user, CommonIdsPO ids) throws Exception {
        // ?????????????????????????????????????????????????????????
        List<ManageBusinessInfo> list = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .in(ManageBusinessInfo::getBusinessId, ids.getIds()));
        if (null != list && !list.isEmpty()) {
            throw new ManageStarException("???????????????????????????????????????");
        }
        // ???????????????????????????
        boolean flash = false;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = true;
        }
        List<ManageBusiness> removeIds = this.list(new LambdaQueryWrapper<ManageBusiness>().eq(flash, ManageBusiness::getCreateAccountId, user.getId())
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .in(ManageBusiness::getId, ids.getIds()));
        // ????????????
        removeIds.stream().forEach(businessInfo -> {
            businessInfo.setStatus(Constant.BaseNumberManage.ZERO);
        });
        this.updateBatchById(removeIds);
    }

    @Override
    public Result<BusinessInfoListVO> getBusinessInfoList(UserVO user, QueryBusinessInfoPO po) throws Exception {
        boolean flash = true;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = false;
        }
        // ????????????
        Page<ManageBusiness> manageBusinessPage = this.getBaseMapper().selectPage(new Page<ManageBusiness>(po.getCurrentPage(), po.getPageSize()), new LambdaQueryWrapper<ManageBusiness>()
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .eq(flash, ManageBusiness::getCreateAccountId, user.getId())
                .eq(po.getBusinessSucess() != null, ManageBusiness::getBusinessSucess, po.getBusinessSucess())
                .eq(po.getCollectMoney() != null, ManageBusiness::getCollectMoney, po.getCollectMoney())
                .like(StrUtil.isNotBlank(po.getBusinessName()), ManageBusiness::getCreateUserName, po.getBusinessName())
                .orderByDesc(ManageBusiness::getCreateTime));
        return Result.ok().resultPage(BeanUtil.copyToList(manageBusinessPage.getRecords(), BusinessInfoListVO.class), manageBusinessPage.getCurrent(), manageBusinessPage.getSize(), manageBusinessPage.getTotal());
    }

    @Override
    public void updateBusinessInfo(UserVO user, UpdateBusinessInfoPO businessInfo) throws Exception {
        boolean flash = true;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = false;
        }
        // ??????????????????
        ManageBusiness manageBusiness = this.getBaseMapper().selectByPrimaryKey(businessInfo.getId());
        if (!flash && !manageBusiness.getCreateAccountId().equals(user.getId())) {
            throw new ManageStarException("?????????????????????????????????");
        }
        ManageBusiness manageBusinessInfo = BeanUtil.toBean(businessInfo, ManageBusiness.class);
        manageBusinessInfo.setUpdateTime(new Date());
        manageBusinessInfo.setUpdateAccountId(user.getId());

        this.updateById(manageBusinessInfo);
    }

    @Override
    public BusinessInfoVO getBusinessInfo(UserVO user, Integer businessId) throws Exception {
        boolean flash = true;
        // ????????????
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = false;
        }
        ManageBusiness manageBusiness = this.getBaseMapper().selectOne(new LambdaQueryWrapper<ManageBusiness>()
                .eq(ManageBusiness::getId, businessId)
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .eq(flash, ManageBusiness::getCreateAccountId, user.getId()));
        // ?????????
        BusinessInfoVO businessInfoVO = BeanUtil.toBean(manageBusiness, BusinessInfoVO.class);
        return businessInfoVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertManageRemarkInfo(UserVO user, ManageBusinessRemark remark) throws Exception {
        boolean flash = true;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            flash = false;
        }

        // ????????????????????????
        ManageBusinessInfo manageBusinessInfo = this.taskBusinessService.getById(remark.getBusinessInfoId());
        if (manageBusinessInfo.getStaffSubmit() == Constant.StaffSubmitType.SUBMIT_NO) {
            throw new ManageStarException("???????????????????????????????????????????????????");
        }
        // ????????????????????????
        if(manageBusinessInfo.getFinish() == Constant.TaskBusinessType.THREE && flash){
            throw new ManageStarException("???????????????????????????????????????");
        }

        // ????????????????????????????????????????????????
        if (remark.getBusinessInfoId() == null) {
            throw new ManageStarException("??????id???????????????");
        }
        ManageBusinessInfo beforeTaskInfo = this.taskBusinessService.getBeforeTaskInfo(remark.getBusinessInfoId());

        if (beforeTaskInfo != null) {
            if (beforeTaskInfo.getConfirmIssue() == Constant.ConfirmTaskType.ZERO || !beforeTaskInfo.getFinish().equals(Constant.TaskBusinessType.THREE)) {
                throw new ManageStarException("?????????????????????????????????????????????????????????");
            }
        }

        this.businessRemarkMapper.insertSelective(remark);

    }

}
