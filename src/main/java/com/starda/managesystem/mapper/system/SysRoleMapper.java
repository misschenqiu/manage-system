package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends MPJBaseMapper<SysRole> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 批量修改role 状态
     * @param roleList
     * @throws Exception
     */
    void updateRoleListById(@Param("roleList") List<Integer> roleList) throws Exception;

}