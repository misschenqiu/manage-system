package com.starda.managesystem.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.system.SysAddressMapper;
import com.starda.managesystem.mapper.system.SysMenuMapper;
import com.starda.managesystem.pojo.ManageAddress;
import com.starda.managesystem.pojo.SysAddress;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.pojo.vo.AddressVO;
import com.starda.managesystem.service.IAddressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: AddressServiceImpl
 * @Author: chenqiu
 * @Description: 地址添加 （管理系统路由）
 * @Date: 2021/8/23 23:49
 * @Version: 1.0
 */

@Service
@Log4j2
public class AddressServiceImpl extends ServiceImpl<SysAddressMapper, SysAddress> implements IAddressService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public void insertAddress(AddressUrlPO po) throws Exception {
        // 1.添加后台路径 子类 和 父类
        List<String> pNameList = new ArrayList<String>(Arrays.asList(po.getPName().split("/")));
        List<String> addressUrlList = new ArrayList<String>(Arrays.asList(po.getAddressUrl().split(",")));

        // 路劲子级
        SysMenu menu = new SysMenu();
        menu.setMenu_name(pNameList.get(pNameList.size() - Constant.BaseNumberManage.ONE));
        menu.setCode(po.getSmilName());
        menu.setCreate_time(new Date());
        menu.setUrl(po.getUrl());
        menu.setAddress_url(addressUrlList.get(addressUrlList.size() - Constant.BaseNumberManage.ONE));
        menu.setRemark(po.getRemark());
        menuMapper.insertSelectiveMenu(menu);
        Integer menuId = menu.getId();
                log.info("添加权限，路径信息 menuId->{}" + menuId);
        int pId = 0;
        // 父级
        for (int i = 0; i < pNameList.size() - Constant.BaseNumberManage.ONE; i++) {
            try {
                if(i == pNameList.size() - Constant.BaseNumberManage.TWO) {
                    SysAddress address = new SysAddress();
                    address.setAddressName(pNameList.get(i));
                    address.setAddressUrl(addressUrlList.get(i));
                    address.setChildren(Constant.BaseStringInfoManage.CHILDREN_NO);
                    address.setMenuId(menuId);
                    address.setPId(pId);
                    this.baseMapper.insertSelective(address);
                    pId = address.getId();
                }else {
                    SysAddress address = new SysAddress();
                    address.setAddressName(pNameList.get(i));
                    address.setAddressUrl(addressUrlList.get(i));
                    address.setChildren(Constant.BaseStringInfoManage.CHILDREN_YES);
                    address.setPId(pId);
                    this.baseMapper.insertSelective(address);
                    pId = address.getId();
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new ManageStarException(ExceptionEnums.ADDRESS_URL.getCode(), ExceptionEnums.ADDRESS_URL.getMessage());
            }
        }
    }

    @Override
    public void removeMenuAddress(Integer id, Integer children) throws Exception {

        switch (children){
            // 无子级
            case Constant.BaseStringInfoManage.CHILDREN_NO:
                this.menuMapper.deleteByPrimaryKey(id);
            // 有子级
            case Constant.BaseStringInfoManage.CHILDREN_YES:
                this.baseMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public Integer addManageAddress(String addressName) throws Exception {
        ManageAddress address = new ManageAddress();
        address.setAddressName(addressName);
        address.setAddressCode(UUID.fastUUID().toString().replaceAll("-", ""));
        address.setStatus(Constant.BaseStringInfoManage.CHILDREN_YES);

        this.baseMapper.insertAddress(address);
        return address.getId();
    }

    @Override
    public IPage<AddressVO> getManageAddress(Integer currentPage, Integer pageSize) throws Exception {

        IPage<AddressVO> addressVOIPage = this.baseMapper.getAddressList(new Page<AddressVO>(currentPage, pageSize));

        return addressVOIPage;
    }

    @Override
    public void removeManageAddress(Integer addressId) throws Exception {

        this.baseMapper.updateAddress(addressId);

    }
}
