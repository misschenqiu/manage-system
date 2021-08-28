package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.dto.RoleListDTO;
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

    /**
     * 查询账号角色
     * @param accountId
     * @return
     * @throws Exception
     */
    List<RoleListDTO> selectRoleByAccountId(Integer accountId) throws Exception;

    /**
     * 查询账号角色
     * @param accountIds
     * @return
     * @throws Exception
     */
    List<RoleListDTO> selectRoleByAccountIds(@Param("accountIds") List<Integer> accountIds) throws Exception;

}