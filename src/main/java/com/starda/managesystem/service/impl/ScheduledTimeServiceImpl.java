package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.business.ManageReCompMapper;
import com.starda.managesystem.mapper.business.ManageReminderMapper;
import com.starda.managesystem.pojo.ManageCompany;
import com.starda.managesystem.pojo.ManageReComp;
import com.starda.managesystem.pojo.ManageReminder;
import com.starda.managesystem.pojo.dto.CompanySmilDTO;
import com.starda.managesystem.pojo.po.RemoveIdsPO;
import com.starda.managesystem.pojo.po.reminder.InsertReminderPO;
import com.starda.managesystem.pojo.po.reminder.ReminderQueryPO;
import com.starda.managesystem.pojo.po.reminder.ReminderUpdatePO;
import com.starda.managesystem.pojo.vo.ReminderListVO;
import com.starda.managesystem.pojo.vo.company.CompanySmilVO;
import com.starda.managesystem.service.IScheduledTimeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: ScheduledTimeServiceImpl
 * @Author: chenqiu
 * @Description: 时间定时器
 * @Date: 2021/8/31 0:22
 * @Version: 1.0
 */

@Service
@Log4j2
public class ScheduledTimeServiceImpl extends ServiceImpl<ManageReminderMapper, ManageReminder> implements IScheduledTimeService {

    @Autowired
    private ManageReCompMapper reCompMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertReminderInfo(UserVO user, InsertReminderPO reminder) throws Exception {
        ManageReminder manageReminder = BeanUtil.toBean(reminder, ManageReminder.class);
        manageReminder.setCreateTime(new Date());
        manageReminder.setCreateUserName(user.getStaffName());
        manageReminder.setStatus(Constant.BaseNumberManage.ONE);
        manageReminder.setCreateAccountId(user.getId());
        this.getBaseMapper().insertSelective(manageReminder);
        // 绑定公司
        if(null == reminder.getCompanyIds() || reminder.getCompanyIds().isEmpty()){
            return;
        }
        List<ManageReComp> reComps = new ArrayList<ManageReComp>();
        reminder.getCompanyIds().stream().forEach(redComp ->{
            ManageReComp reComp = new ManageReComp();
            reComp.setReminderId(manageReminder.getId());
            reComp.setCompanyId(redComp);
            reComps.add(reComp);
        });
        for (ManageReComp reComp : reComps) {
            this.reCompMapper.insertSelective(reComp);
        }
        log.info("添加消息成功");
    }

    @Override
    public void removeReminderInfo(UserVO user,  RemoveIdsPO po) throws Exception {
            // 判断是否是大管理员
            boolean flash = true;
            if(user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
                flash = false;
            }
            if(null == po.getIdList() || po.getIdList().isEmpty()){
                return;
            }
            ManageReminder reminder = new ManageReminder();
            switch (po.getType()){
                case Constant.ReminderType.UPDATE_REMINDER:
                    if(po.getReminderOpen() == null){
                        throw new ManageStarException("选择是否开启！");
                    }
                    reminder.setReminderOpen(po.getReminderOpen());
                    break;
                case Constant.ReminderType.DELETE_REMINDER:
                    reminder.setStatus(Constant.BaseNumberManage.ZERO);
                    break;
                case Constant.ReminderType.UPDATE_AGAIN:
                    if (po.getAgain() == null){
                        throw new ManageStarException("是否再次提前！");
                    }
                    reminder.setAgain(po.getAgain());
                    break;
            }
            this.getBaseMapper().update(reminder, new LambdaQueryWrapper<ManageReminder>()
                    .eq(flash, ManageReminder::getCreateAccountId, user.getId())
                    .in(ManageReminder::getId,po.getIdList()));
            log.info("修改成功");
    }

    @Override
    public Result<ReminderListVO> getReminderList(UserVO user, ReminderQueryPO po) throws Exception {
        // 判断是否是大管理员
        boolean flash = true;
        if(user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            flash = false;
        }
        Page<ManageReminder> page = this.page(new Page<ManageReminder>(po.getCurrentPage(), po.getPageSize()), new LambdaQueryWrapper<ManageReminder>()
                .eq(ManageReminder::getStatus, Constant.BaseNumberManage.ONE)
                .eq(flash, ManageReminder::getCreateAccountId, user.getId())
                .ge(po.getStartTime() != null, ManageReminder::getCreateTime, po.getStartTime())
                .le(po.getEndTime() != null, ManageReminder::getCreateTime, po.getEndTime()));
        // 获取单位信息
        List<ReminderListVO> reminderListVOS = BeanUtil.copyToList(page.getRecords(), ReminderListVO.class);
        List<Integer> reminderIds = reminderListVOS.stream().map(reminderId->reminderId.getId()).collect(Collectors.toList());
        if(null == reminderIds || reminderIds.isEmpty()){
            return Result.ok().resultPage(reminderListVOS, page.getCurrent(), page.getSize(), page.getTotal());
        }
        // 获取到单位信息
        List<CompanySmilDTO> manageCompanies = this.reCompMapper.selectJoinList(CompanySmilDTO.class, new MPJLambdaWrapper<ManageReComp>()
                .selectAll(ManageCompany.class)
                .select(ManageReComp::getReminderId)
                .leftJoin(ManageCompany.class, ManageCompany::getId, ManageReComp::getCompanyId)
                .in(ManageReComp::getReminderId, reminderIds)
                .eq(ManageCompany::getCompanyStatus, Constant.BaseNumberManage.ONE));
        // 分组
        Map<Integer, List<CompanySmilDTO>> integerListMap = manageCompanies.stream().collect(Collectors.groupingBy(CompanySmilDTO::getReminderId));
        reminderListVOS.stream().forEach(reminderId->{
            reminderId.setCompanyName(BeanUtil.copyToList(integerListMap.get(reminderId.getId()),CompanySmilVO.class));
        });

        return Result.ok().resultPage(reminderListVOS, page.getCurrent(), page.getSize(), page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReminderInfo(UserVO user, ReminderUpdatePO po) throws Exception {
        // 检验是否存在
        Map<String, Object> map = this.getMap(new LambdaQueryWrapper<ManageReminder>().eq(ManageReminder::getId, po.getId()).eq(ManageReminder::getStatus, Constant.BaseNumberManage.ONE));
        if(map == null || map.isEmpty()){
            throw new ManageStarException("请刷新");
        }
        ManageReminder reminder = BeanUtil.toBean(po, ManageReminder.class);
        reminder.setUpdateTime(new Date());
        this.getBaseMapper().updateByPrimaryKeySelective(reminder);
        // 修改中间表
        this.reCompMapper.delete(new LambdaQueryWrapper<ManageReComp>().eq(ManageReComp::getReminderId, po.getId()));
        // 添加
        List<ManageReComp> reComps = new ArrayList<ManageReComp>();
        po.getCompanyIds().stream().forEach(redComp ->{
            ManageReComp reComp = new ManageReComp();
            reComp.setReminderId(po.getId());
            reComp.setCompanyId(redComp);
            reComps.add(reComp);
        });
        for (ManageReComp reComp : reComps) {
            this.reCompMapper.insertSelective(reComp);
        }
        log.info("添加消息成功");
    }

    @Override
    public ReminderListVO getReminderInfo(UserVO user, Integer reminderId) throws Exception {
        // 判断是否是大管理员
        boolean flash = true;
        if(user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            flash = false;
        }
        ManageReminder reminder = this.getBaseMapper().selectOne(new LambdaQueryWrapper<ManageReminder>().eq(ManageReminder::getId, reminderId)
                .eq(flash, ManageReminder::getCreateAccountId, user.getId()));
        ReminderListVO reminderListVO = BeanUtil.toBean(reminder, ReminderListVO.class);
        // 查询公司
        List<ManageCompany> manageCompanies = this.reCompMapper.selectJoinList(ManageCompany.class, new MPJLambdaWrapper<ManageReComp>()
                .selectAll(ManageCompany.class)
                .leftJoin(ManageCompany.class, ManageCompany::getId, ManageReComp::getCompanyId)
                .eq(ManageReComp::getReminderId, reminderId)
                .eq(ManageCompany::getCompanyStatus, Constant.BaseNumberManage.ONE));
        reminderListVO.setCompanyName(BeanUtil.copyToList(manageCompanies, CompanySmilVO.class));
        log.info("复制成功");
        return reminderListVO;
    }
}
