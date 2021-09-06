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
import com.starda.managesystem.pojo.po.CommonIdsPO;
import com.starda.managesystem.pojo.po.business.*;
import com.starda.managesystem.pojo.vo.business.BusinessInfoListVO;
import com.starda.managesystem.pojo.vo.business.BusinessInfoVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoLIstVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoRemarkVO;
import com.starda.managesystem.service.IAppTaskBusinessService;
import com.starda.managesystem.service.IBusinessTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //1. 查询业务是否又数据
        List<ManageBusinessInfo> list = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getBusinessId, taskInfo.getBusinessId())
                .orderByDesc(ManageBusinessInfo::getLevel));

        /**
         * 分装参数
         */
        ManageBusinessInfo manageBusinessInfo = BeanUtil.toBean(taskInfo, ManageBusinessInfo.class);
        // 排序 父类
        if (null == list || list.isEmpty()) {
            manageBusinessInfo.setLevel(Constant.BaseNumberManage.ONE);
        } else {
            ManageBusinessInfo businessInfo = list.get(Constant.BaseNumberManage.ZERO);
            manageBusinessInfo.setLevel(businessInfo.getLevel() + Constant.BaseNumberManage.ONE);
            manageBusinessInfo.setPid(businessInfo.getId());
        }
        // 状态
        manageBusinessInfo.setStatus(Constant.BaseNumberManage.ONE);
        manageBusinessInfo.setFinish(Constant.BaseNumberManage.ZERO);
        // 创建人信息
        manageBusinessInfo.setCreateAccountId(user.getId());
        manageBusinessInfo.setCreateTime(new Date());
        manageBusinessInfo.setCreateUser(user.getStaffName());
        // 添加数据
        this.taskBusinessService.save(manageBusinessInfo);
        log.info("添加任务正常");
        // TODO 提醒对应员工

    }

    @Override
    public void removeTaskInfo(UserVO user, CommonIdsPO ids) throws Exception {
        // 判断是否是大管理员
        boolean flash = false;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = true;
        }
        List<Integer> finishType = new ArrayList<Integer>(Arrays.asList(new Integer[]{Constant.TaskBusinessType.ZERO, Constant.TaskBusinessType.FOUR, Constant.TaskBusinessType.FIVE}));
        List<ManageBusinessInfo> list = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(flash, ManageBusinessInfo::getCreateAccountId, user.getId())
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .in(ManageBusinessInfo::getFinish, finishType)
                .in(ManageBusinessInfo::getId, ids.getIds()));
        // 修改内容
        list.stream().forEach(param -> {
            param.setStatus(Constant.BaseNumberManage.ZERO);
        });
        this.taskBusinessService.updateBatchById(list);
        log.info("删除成功");
    }

    @Override
    public Result<TaskInfoLIstVO> getTaskInfoList(UserVO user, TaskInfoQueryPO po) throws Exception {
        //1. 获取到任务信息
        Page<ManageBusinessInfo> page = this.taskBusinessService.page(new Page<ManageBusinessInfo>(po.getCurrentPage(), po.getPageSize()), new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusinessInfo::getBusinessId, po.getBusinessId())
                .orderByAsc(ManageBusinessInfo::getLevel));
        // 2.查询业务管理员备注
        List<TaskInfoLIstVO> taskInfoLIstVOS = BeanUtil.copyToList(page.getRecords(), TaskInfoLIstVO.class);
        List<Integer> businessInfoIds = taskInfoLIstVOS.stream().map(taskInfo -> taskInfo.getId()).collect(Collectors.toList());
        List<TaskInfoRemarkVO> taskInfoRemarkVOS = this.businessRemarkMapper.selectJoinList(TaskInfoRemarkVO.class, new MPJLambdaWrapper<ManageBusinessRemark>()
                .selectAll(ManageBusinessRemark.class)
                .in(businessInfoIds != null && !businessInfoIds.isEmpty(), ManageBusinessRemark::getBusinessInfoId, businessInfoIds));

        // 处理数据
        Map<Integer, List<TaskInfoRemarkVO>> integerListMap = taskInfoRemarkVOS.stream().collect(Collectors.groupingBy(TaskInfoRemarkVO::getBusinessInfoId));
        taskInfoLIstVOS.stream().forEach(task -> {
            List<TaskInfoRemarkVO> taskInfoRemarkList = integerListMap.get(task.getId());
            if (null != taskInfoRemarkList && !taskInfoRemarkList.isEmpty()) {
                task.setManageRemarkVO(taskInfoRemarkList.get(Constant.BaseNumberManage.ZERO));
            }
        });
        return Result.ok().resultPage(taskInfoLIstVOS, page.getCurrent(), page.getSize(), page.getTotal());
    }

    @Override
    public void confirmTaskInfo(UserVO user, ConfirmTaskPO po) throws Exception {

    }

    /*****         业务信息          *****/

    @Override
    public void insertBusinessInfo(UserVO user, InsertBusinessInfoPO business) throws Exception {

        ManageBusiness manageBusiness = BeanUtil.toBean(business, ManageBusiness.class);
        // 未回款 未完成 状态好 创建时间 创建人
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
        // 查询是否有正在完成的任务，或者已经完成
        List<ManageBusinessInfo> list = this.taskBusinessService.list(new LambdaQueryWrapper<ManageBusinessInfo>()
                .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                .in(ManageBusinessInfo::getBusinessId, ids.getIds()));
        if (null != list && !list.isEmpty()) {
            throw new ManageStarException("业务存在任务详情，不能删除");
        }
        // 查询是否是自己创建
        boolean flash = false;
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = true;
        }
        List<ManageBusiness> removeIds = this.list(new LambdaQueryWrapper<ManageBusiness>().eq(flash, ManageBusiness::getCreateAccountId, user.getId())
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .in(ManageBusiness::getId, ids.getIds()));
        // 修改状态
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
        // 获取数据
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
        // 获取修改信息
        ManageBusiness manageBusiness = this.getBaseMapper().selectByPrimaryKey(businessInfo.getId());
        if (!flash && !manageBusiness.getCreateAccountId().equals(user.getId())) {
            throw new ManageStarException("对不起，你没有需改权限");
        }
        ManageBusiness manageBusinessInfo = BeanUtil.toBean(businessInfo, ManageBusiness.class);
        manageBusinessInfo.setUpdateTime(new Date());
        manageBusinessInfo.setUpdateAccountId(user.getId());

        this.updateById(manageBusinessInfo);
    }

    @Override
    public BusinessInfoVO getBusinessInfo(UserVO user, Integer businessId) throws Exception {
        boolean flash = true;
        // 判断权限
        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            flash = false;
        }
        ManageBusiness manageBusiness = this.getBaseMapper().selectOne(new LambdaQueryWrapper<ManageBusiness>()
                .eq(ManageBusiness::getId, businessId)
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .eq(flash, ManageBusiness::getCreateAccountId, user.getId()));
        // 转参数
        BusinessInfoVO businessInfoVO = BeanUtil.toBean(manageBusiness, BusinessInfoVO.class);
        return businessInfoVO;
    }
}
