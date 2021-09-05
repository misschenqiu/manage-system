package com.starda.managesystem.mapper.business;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageBusinessRemark;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageBusinessRemarkMapper extends MPJBaseMapper<ManageBusinessRemark> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManageBusinessRemark record);

    ManageBusinessRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageBusinessRemark record);

    int updateByPrimaryKey(ManageBusinessRemark record);
}