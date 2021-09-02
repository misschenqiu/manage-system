package com.starda.managesystem.mapper.business;

import com.starda.managesystem.pojo.ManageBusinessInfo;

public interface ManageBusinessInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManageBusinessInfo record);

    int insertSelective(ManageBusinessInfo record);

    ManageBusinessInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageBusinessInfo record);

    int updateByPrimaryKey(ManageBusinessInfo record);
}