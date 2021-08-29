package com.starda.managesystem.mapper.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysStaff;
import com.starda.managesystem.pojo.po.staff.StaffQueryPO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysStaffMapper extends MPJBaseMapper<SysStaff> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysStaff record);

    SysStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysStaff record);

    int updateByPrimaryKey(SysStaff record);

    /**
     * 批量修改员工信息
     * @param staffIds
     * @throws Exception
     */
    void updateByStaffIds(@Param("staffIds")List<Integer> staffIds) throws Exception;

    /**
     * 通过账号id 删除员工
     * @param staffIds
     * @throws Exception
     */
    void updateByAccountIds(@Param("accountIds")List<Integer> staffIds) throws Exception;

    /**
     * 获取到员工列表
     * @param po
     * @return
     * @throws Exception
     */
    IPage<StaffInfoListVO> getAccountInfoList(Page<StaffInfoListVO> page, @Param("staff") StaffQueryPO po) throws Exception;

}