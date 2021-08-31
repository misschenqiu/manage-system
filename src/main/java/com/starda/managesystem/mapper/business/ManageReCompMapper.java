package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageReComp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageReCompMapper extends MPJBaseMapper<ManageReComp> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageReComp record);

    ManageReComp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageReComp record);

    int updateByPrimaryKey(ManageReComp record);
}