package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends MPJBaseMapper<SysUserRole> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    /**
     * 批量添加角色信息
     * @param userRoles
     * @throws Exception
     */
    void insertRoleUser(@Param("userRoles") List<SysUserRole> userRoles) throws Exception;
}