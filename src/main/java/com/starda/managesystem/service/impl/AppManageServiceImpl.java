package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.pojo.ManageBusiness;
import com.starda.managesystem.pojo.ManageBusinessInfo;
import com.starda.managesystem.pojo.po.app.AppManageQueryBusinessPO;
import com.starda.managesystem.pojo.po.business.TaskInfoQueryPO;
import com.starda.managesystem.pojo.vo.app.AppBusinessInfoListVO;
import com.starda.managesystem.pojo.vo.app.AppBusinessListVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoLIstVO;
import com.starda.managesystem.service.IAppManageService;
import com.starda.managesystem.service.IAppTaskBusinessService;
import com.starda.managesystem.service.IBusinessTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: IAppManageService
 * @Author: chenqiu
 * @Description:管理员 app 列表
 * @Date: 2021/9/17 12:48
 * @Version: 1.0
 */

@Service
@Log4j2
public class AppManageServiceImpl implements IAppManageService {

    @Autowired
    private IBusinessTaskService businessTaskService;

    @Autowired
    private IAppTaskBusinessService taskBusinessService;

    @Override
    public Result getBusinessList(UserVO user, AppManageQueryBusinessPO po) throws Exception {

        LambdaQueryWrapper<ManageBusiness> wrapper = new LambdaQueryWrapper<ManageBusiness>()
                .eq(ManageBusiness::getStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageBusiness::getCreateAccountId, user.getId())
                .eq(po.getBusinessSucess() != null, ManageBusiness::getBusinessSucess, po.getBusinessSucess())
                .eq(po.getCollectMoney() != null, ManageBusiness::getCollectMoney, po.getCollectMoney())
                .like(StrUtil.isNotBlank(po.getBusinessName()), ManageBusiness::getCreateUserName, po.getBusinessName())
                .orderByDesc(ManageBusiness::getCreateTime);

        // 获取数据 查看类型1.待完成 2.已完成
        switch (po.getType()){
            case Constant.AppQueryType.ONE:
                wrapper.eq(ManageBusiness::getBusinessSucess, Constant.BaseNumberManage.ZERO);
                break;
            case Constant.AppQueryType.TWO:
                wrapper.eq(ManageBusiness::getBusinessSucess, Constant.BaseNumberManage.ONE);
                break;
        }
        Page<ManageBusiness> manageBusinessPage = this.businessTaskService.getBaseMapper()
                .selectPage(new Page<ManageBusiness>(po.getCurrentPage(), po.getPageSize()), wrapper);

        List<AppBusinessListVO> appBusinessInfoListVOS = BeanUtil.copyToList(manageBusinessPage.getRecords(), AppBusinessListVO.class);
        return Result.ok().resultPage(appBusinessInfoListVOS,  manageBusinessPage.getCurrent(), manageBusinessPage.getSize(), manageBusinessPage.getTotal());
    }

    @Override
    public Result<AppBusinessInfoListVO> getAppTaskInfoList(UserVO user, TaskInfoQueryPO po) throws Exception {
        Page<ManageBusinessInfo> page = this.taskBusinessService.page(new Page<ManageBusinessInfo>(po.getCurrentPage(), po.getPageSize()),
                new LambdaQueryWrapper<ManageBusinessInfo>()
                        .eq(ManageBusinessInfo::getStatus, Constant.BaseNumberManage.ONE)
                        .eq(ManageBusinessInfo::getBusinessId, po.getBusinessId())
                        .orderByAsc(ManageBusinessInfo::getLevel));
        List<AppBusinessInfoListVO> appBusinessInfoListVOS = BeanUtil.copyToList(page.getRecords(), AppBusinessInfoListVO.class);
        return Result.ok().resultPage(appBusinessInfoListVOS,  page.getCurrent(), page.getSize(), page.getTotal());
    }
}
