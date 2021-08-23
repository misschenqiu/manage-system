package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysAddressMapper extends MPJBaseMapper<SysAddress> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysAddress record);

    SysAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAddress record);

    int updateByPrimaryKey(SysAddress record);
}