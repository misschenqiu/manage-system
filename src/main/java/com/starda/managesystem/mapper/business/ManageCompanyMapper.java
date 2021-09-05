package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageCompany;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageCompanyMapper extends MPJBaseMapper<ManageCompany> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageCompany record);

    ManageCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageCompany record);

    int updateByPrimaryKey(ManageCompany record);
}