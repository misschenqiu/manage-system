package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysStaff;

public interface SysStaffMapper extends MPJBaseMapper<SysStaff> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysStaff record);

    SysStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysStaff record);

    int updateByPrimaryKey(SysStaff record);
}