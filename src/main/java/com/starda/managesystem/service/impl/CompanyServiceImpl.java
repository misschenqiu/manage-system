package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.mapper.business.ManageCompanyMapper;
import com.starda.managesystem.pojo.ManageCompany;
import com.starda.managesystem.pojo.po.company.CompanyInsertPO;
import com.starda.managesystem.pojo.po.company.CompanyQueryPO;
import com.starda.managesystem.pojo.po.company.CompanyUpdatePO;
import com.starda.managesystem.pojo.vo.company.CompanyListVO;
import com.starda.managesystem.service.ICompanyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: CompanyServiceImpl
 * @Author: chenqiu
 * @Description: 单位管理业务成
 * @Date: 2021/8/30 20:38
 * @Version: 1.0
 */

@Service
@Log4j2
public class CompanyServiceImpl extends ServiceImpl<ManageCompanyMapper, ManageCompany> implements ICompanyService {

    @Override
    public void insertCompanyInfo(UserVO user, CompanyInsertPO company) throws Exception {
        ManageCompany manageCompany = BeanUtil.toBean(company, ManageCompany.class);
        manageCompany.setCreateAccountId(user.getId());
        manageCompany.setCreateTime(new Date());
        manageCompany.setCompanyStatus(Constant.BaseNumberManage.ONE);
        manageCompany.setUserName(user.getStaffName());
        this.getBaseMapper().insertSelective(manageCompany);
        log.info("添加单位成功");
    }

    @Override
    public void removeCompanyInfo(UserVO user, List<Integer> companyIds) throws Exception {
        if(companyIds == null || companyIds.isEmpty()){
            return;
        }

        ManageCompany company = new ManageCompany();
        company.setCompanyStatus(Constant.BaseNumberManage.ZERO);
        this.getBaseMapper().update(company, new LambdaQueryWrapper<ManageCompany>().in(ManageCompany::getId, companyIds));
        log.info("删除单位成功");
    }

    @Override
    public void updateCompanyInfo(UserVO user, CompanyUpdatePO company) throws Exception {
        // 检查单位信息
        ManageCompany companyInfo = this.getBaseMapper().selectOne(new LambdaQueryWrapper<ManageCompany>()
                .eq(ManageCompany::getCompanyStatus, Constant.BaseNumberManage.ONE)
                .eq(ManageCompany::getId, company.getId()));
        if(companyInfo == null){
            return;
        }
        ManageCompany manageCompany = BeanUtil.toBean(company, ManageCompany.class);
        manageCompany.setUpdateTime(new Date());
        this.getBaseMapper().updateByPrimaryKeySelective(manageCompany);
        log.info("修改单位成功");
    }

    @Override
    public Result<CompanyListVO> getCompanyInfoList(UserVO user, CompanyQueryPO po) throws Exception {
        boolean flash = false;
        // 判断权限
        if(user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            flash = true;
        }

        Page<ManageCompany> manageCompanyPage = this.getBaseMapper().selectPage(new Page<ManageCompany>(po.getCurrentPage(), po.getPageSize()), new LambdaQueryWrapper<ManageCompany>()
                .eq(ManageCompany::getCompanyStatus, Constant.BaseNumberManage.ONE)
                .eq(flash, ManageCompany::getCreateAccountId, user.getId())
                .eq(StrUtil.isNotBlank(po.getCompanyPhone()), ManageCompany::getCompanyPhone, po.getCompanyPhone())
                .like(StrUtil.isNotBlank(po.getCompanyName()), ManageCompany::getCompanyName, po.getCompanyName())
                .orderByDesc(ManageCompany::getCreateTime));
        log.info("获取到数据条数 num ->{}" + manageCompanyPage.getTotal());
        List<CompanyListVO> companyListVOS = BeanUtil.copyToList(manageCompanyPage.getRecords(), CompanyListVO.class);
        // 数据脱敏处理
        companyListVOS.stream().forEach(company->{
            if(StrUtil.isNotBlank(company.getCompanyPhone())){
                company.setCompanyPhone(DesensitizedUtil.mobilePhone(company.getCompanyPhone()));
            }
        });

        return Result.ok().resultPage(companyListVOS, manageCompanyPage.getCurrent(), manageCompanyPage.getSize(), manageCompanyPage.getTotal());
    }

    @Override
    public CompanyListVO getCompanyInfo(UserVO user, Integer companyId) throws Exception {
        boolean flash = false;
        // 判断权限
        if(user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            flash = true;
        }
        // 查看是不是自己操作的
        ManageCompany company = this.getBaseMapper().selectOne(new LambdaQueryWrapper<ManageCompany>()
                .eq(ManageCompany::getId, companyId)
                .eq(ManageCompany::getCompanyStatus, Constant.BaseNumberManage.ONE)
                .eq(flash, ManageCompany::getCreateAccountId, user.getId()));
        if(company == null){
            return null;
        }
        CompanyListVO companyListVO = BeanUtil.toBean(company, CompanyListVO.class);

        return companyListVO;
    }
}
