package com.starda.managesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.company.CompanyInsertPO;
import com.starda.managesystem.pojo.po.company.CompanyQueryPO;
import com.starda.managesystem.pojo.po.company.CompanyUpdatePO;
import com.starda.managesystem.pojo.vo.company.CompanyListVO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: ICompanyService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/30 20:38
 * @Version: 1.0
 */
public interface ICompanyService {

    /**
     * 添加单位信息
     * @param user
     * @param company
     * @throws Exception
     */
    void insertCompanyInfo(UserVO user, CompanyInsertPO company) throws Exception;

    /**
     * 删除单位信息
     * @param user
     * @param companyIds 单位ids
     * @throws Exception
     */
    void removeCompanyInfo(UserVO user, List<Integer> companyIds) throws Exception;

    /**
     * 修改单位信息
     * @param user
     * @param company
     * @throws Exception
     */
    void updateCompanyInfo(UserVO user, CompanyUpdatePO company) throws Exception;

    /**
     * 查询单位信息列表
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    Result<CompanyListVO> getCompanyInfoList(UserVO user, CompanyQueryPO po) throws Exception;

    /**
     * 单位详情
     * @param user
     * @param companyId
     * @return
     * @throws Exception
     */
    CompanyListVO getCompanyInfo(UserVO user, Integer companyId) throws Exception;

}
