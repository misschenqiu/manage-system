package com.starda.managesystem.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starda.managesystem.pojo.SysStaff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysStaffMapper extends BaseMapper<SysStaff> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysStaff record);

    SysStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysStaff record);

    int updateByPrimaryKey(SysStaff record);
}