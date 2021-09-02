package com.starda.managesystem.mapper.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.ManageAddress;
import com.starda.managesystem.pojo.SysAddress;
import com.starda.managesystem.pojo.vo.AddressVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysAddressMapper extends MPJBaseMapper<SysAddress> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysAddress record);

    SysAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAddress record);

    int updateByPrimaryKey(SysAddress record);

    /**
     * 添加管理地址
     * @param address
     * @return
     */
    int insertAddress(ManageAddress address);

    /**
     * 查询是否又数据
     * @param addressName
     * @return
     */
    AddressVO getManageAddress(String addressName);

    /**
     * 获取到管理地址列表
     * @return
     */
    List<AddressVO> getAddressList();

    /**
     * 删除管理地址
     * @param addressId
     */
    void updateAddress(@Param("addressId") Integer addressId);

    /**
     * 获取全部父级id
     * @param menuIdList
     * @return
     */
    List<Map<String,Object>> getParentAddressList(List<Integer> menuIdList);

}