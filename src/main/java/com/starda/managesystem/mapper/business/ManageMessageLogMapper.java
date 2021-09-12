package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageMessageLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMessageLogMapper extends MPJBaseMapper<ManageMessageLog> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageMessageLog record);

    ManageMessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageMessageLog record);

    int updateByPrimaryKey(ManageMessageLog record);
}