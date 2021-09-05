package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageBusiness;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageBusinessMapper extends MPJBaseMapper<ManageBusiness> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageBusiness record);

    ManageBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageBusiness record);

    int updateByPrimaryKey(ManageBusiness record);
}