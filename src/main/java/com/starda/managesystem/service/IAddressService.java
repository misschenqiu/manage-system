package com.starda.managesystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.SysAddress;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.pojo.vo.AddressVO;
import com.starda.managesystem.pojo.vo.MenuAddressVO;
import com.starda.managesystem.pojo.vo.role.MenuAddressListVO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IAddressService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/23 23:48
 * @Version: 1.0
 */
public interface IAddressService extends IService<SysAddress> {

    /**
     * 添加路由
     * @param po 参数
     * @throws Exception
     */
    void insertAddress(AddressUrlPO po) throws Exception;

    /**
     * 获取菜单路由
     * @param vo 用户信息
     * @return
     * @throws Exception
     */
    List<MenuAddressListVO> getAddressList(UserVO vo) throws Exception;

    /**
     * 获取前端页面返回数据
     * @param vo
     * @return
     * @throws Exception
     */
    List<MenuAddressVO> getMenuAddressList(UserVO vo) throws Exception;

    /**
     * 获取前端页面返回数据
     * @param vo
     * @return
     * @throws Exception
     */
    List<MenuAddressListVO> getMenuAddressChoiceList(UserVO vo) throws Exception;

    /**
     * 删除 路由
     * @param id 路由id
     * @param children 是否有子级 0.否 1 是
     * @throws Exception
     */
    void removeMenuAddress(Integer id, Integer children) throws Exception;

    /**
     * 添加管理地址
     * @param addressName
     * @throws Exception
     * @return
     */
    String addManageAddress(String addressName) throws Exception;

    /**
     * 获取管理地址数据
     * @return
     * @throws Exception
     */
    List<AddressVO> getManageAddress() throws Exception;

    /**
     * 删除管理地址
     * @param addressId
     * @throws Exception
     */
    void removeManageAddress(Integer addressId) throws Exception;

    /**
     * 链表查询
     * @param mapper
     * @return
     * @throws Exception
     */
    List<SysAddress> getMPJSysAddressList(MPJLambdaWrapper<SysAddress> mapper) throws Exception;

}
