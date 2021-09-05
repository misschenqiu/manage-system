package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageBusinessInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageBusinessInfoMapper extends MPJBaseMapper<ManageBusinessInfo> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageBusinessInfo record);

    ManageBusinessInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageBusinessInfo record);

    int updateByPrimaryKey(ManageBusinessInfo record);
}