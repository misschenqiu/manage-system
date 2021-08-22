package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuMapper extends MPJBaseMapper<SysRoleMenu> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}