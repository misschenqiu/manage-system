package com.starda.managesystem.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.interfaces.MPJBaseJoin;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.system.SysAddressMapper;
import com.starda.managesystem.mapper.system.SysMenuMapper;
import com.starda.managesystem.mapper.system.SysRoleMenuMapper;
import com.starda.managesystem.pojo.ManageAddress;
import com.starda.managesystem.pojo.SysAddress;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.dto.MenuAddressDTO;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.pojo.vo.AddressVO;
import com.starda.managesystem.pojo.vo.MenuAddressVO;
import com.starda.managesystem.pojo.vo.role.MenuRoleChoiceVO;
import com.starda.managesystem.service.IAddressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public void insertAddress(AddressUrlPO po) throws Exception {
        // 1.添加后台路径 子类 和 父类
        List<String> pNameList = new ArrayList<String>(Arrays.asList(po.getPName().split("/")));
        List<String> addressUrlList = new ArrayList<String>(Arrays.asList(po.getAddressUrl().split(",")));
        List<String> pathList = new ArrayList<>(Arrays.asList(po.getPath().split(",")));
        List<String> iconList = new ArrayList<>(Arrays.asList(po.getIcon().split(" ")));

        // 路劲子级
        SysMenu menu = new SysMenu();
        menu.setMenu_name(pNameList.get(pNameList.size() - Constant.BaseNumberManage.ONE));
        menu.setCode(po.getSmilName());
        menu.setCreate_time(new Date());
        menu.setUrl(po.getUrl());
        menu.setAddress_url(addressUrlList.get(addressUrlList.size() - Constant.BaseNumberManage.ONE));
        menu.setRemark(po.getRemark());
        menu.setRoute(pathList.get(pathList.size() - Constant.BaseNumberManage.ONE));
        menu.setIcon(iconList.get(iconList.size() - Constant.BaseNumberManage.ONE));
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
                    address.setRoute(pathList.get(i));
                    address.setIcon(iconList.get(i));
                    this.baseMapper.insertSelective(address);
                    pId = address.getId();
                }else {
                    SysAddress address = new SysAddress();
                    address.setAddressName(pNameList.get(i));
                    address.setAddressUrl(addressUrlList.get(i));
                    address.setChildren(Constant.BaseStringInfoManage.CHILDREN_YES);
                    address.setPId(pId);
                    address.setRoute(pathList.get(i));
                    address.setIcon(iconList.get(i));
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
    public List<MenuAddressDTO> getAddressList(UserVO vo) throws Exception {

        // 1. 获取改角色下的所有资源
        List<SysRole> roleList = JSONArray.parseArray(vo.getRoleListString(), SysRole.class);
        log.info("角色信息是否转换成功-》" + roleList.size());
        // 权限数据
        List<MenuAddressDTO> menuAddressDTOS = new ArrayList<MenuAddressDTO>();
        List<SysRole> roleAdmin = roleList.stream().filter(param->param.getRole_code().equals(Constant.BaseStringInfoManage.MANAGE)).collect(Collectors.toList());
        if(null == roleAdmin || roleAdmin.isEmpty()){
            List<Integer> role = roleList.stream().map(roleId->roleId.getId()).collect(Collectors.toList());
            log.info("角色 id ->{}" + role);
            if(role.size() == 0 || role.isEmpty()){
                role = null;
            }
            // 一般权限
            menuAddressDTOS = this.sysRoleMenuMapper.getMenuAddressList(role);
        }else {
            // 最大权限
            menuAddressDTOS = this.sysRoleMenuMapper.getMenuAddressListAll();
        }


        // 获取到全部父级
        List<SysAddress> addressList = this.baseMapper.selectList(new QueryWrapper<SysAddress>());

        // 便利处理数据
        this.handleAddress(addressList, menuAddressDTOS);

        return menuAddressDTOS;
    }

    @Override
    public List<MenuAddressVO> getMenuAddressList(UserVO vo) throws Exception {

        // 1. 获取改角色下数据
        List<MenuAddressDTO> menuAddressDTOS = this.getAddressList(vo);
        log.info("获取到的角色下所有数据-》{}" + menuAddressDTOS.size());
        // 返回数据
        List<MenuAddressVO> menuAddressVOList = new ArrayList<MenuAddressVO>();

        menuAddressDTOS.stream().forEach(data ->{
            menuAddressVOList.add(MenuAddressVO.builder()
                    .id(data.getId())
                    .component(data.getAddressUrl())
                    .title(data.getAddressName())
                    .pId(data.getPId())
                    .path(data.getPath())
                    .icon(data.getIcon())
                    .redirect(data.getRedirect())
                    .build());
        });

        return menuAddressVOList;
    }

    @Override
    public List<MenuRoleChoiceVO> getMenuAddressChoiceList(UserVO vo) throws Exception {

        // 1. 获取改角色下数据
        List<MenuAddressDTO> menuAddressDTOS = this.getAddressList(vo);
        log.info("获取到的角色下所有数据-》{}" + menuAddressDTOS.size());
        // 返回数据
        List<MenuRoleChoiceVO> menuAddressVOList = new ArrayList<MenuRoleChoiceVO>();

        menuAddressDTOS.stream().forEach(data ->{
            menuAddressVOList.add(MenuRoleChoiceVO.builder()
                    .id(data.getId())
                    .title(data.getAddressName())
                    .pId(data.getPId())
                    .icon(data.getIcon())
                    .remark(data.getRemark())
                    .smilName(data.getSmilName())
                    .build());
        });

        return menuAddressVOList;
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

    /**
     * 便利 处理子父级
     * @param addressList 父级
     * @param menuAddressDTOS 子级
     */
    private void handleAddress(List<SysAddress> addressList, List<MenuAddressDTO> menuAddressDTOS){

        // 子级数据
        List<SysAddress> addressListPrent = new ArrayList<SysAddress>();
        List<SysAddress> addressListChildren = new ArrayList<SysAddress>();
        List<MenuAddressDTO> menuAddressDTOSPrent = new ArrayList<MenuAddressDTO>();

        // 取出子级
        for (MenuAddressDTO menuAddressDTO : menuAddressDTOS) {
            for (SysAddress address : addressList) {
                if(address.getMenuId().equals(menuAddressDTO.getId())){
                    menuAddressDTOSPrent.add(MenuAddressDTO.builder()
                            .addressUrl(address.getAddressUrl())
                            .addressName(address.getAddressName())
                            .icon(address.getIcon())
                            .id(address.getId())
                            .pId(address.getId())
                            .path(address.getRoute())
                            .redirect(address.getRedirect())
                            .build());
                    addressListChildren.add(address);
                    // 填充父级id
                    menuAddressDTO.setPId(address.getId());
                }else {
                    addressListPrent.add(address);
                }
            }
        }

        // 遍历子级数据
        this.handleChildren(addressListPrent, addressListChildren, menuAddressDTOSPrent);

        //添加数据
        menuAddressDTOS.addAll(menuAddressDTOSPrent);
    }

    /**
     * 处理迭代数据
     * @param addressListPrent
     * @param addressListChildren
     * @param menuAddressDTOS
     */
    private void handleChildren(List<SysAddress> addressListPrent, List<SysAddress> addressListChildren, List<MenuAddressDTO> menuAddressDTOS){

        // 子级数据
        List<SysAddress> addressPrentList = new ArrayList<SysAddress>();
        List<SysAddress> addressChildrenList = new ArrayList<SysAddress>();

        // 取出子级
        for (SysAddress children : addressListChildren) {
            for (SysAddress prent : addressListPrent) {
                if(prent.getId().equals(children.getPId())){
                    menuAddressDTOS.add(MenuAddressDTO.builder()
                            .addressUrl(prent.getAddressUrl())
                            .addressName(prent.getAddressName())
                            .icon(prent.getIcon())
                            .id(prent.getId())
                            .pId(prent.getPId())
                            .path(prent.getRoute())
                            .redirect(prent.getRedirect())
                            .build());
                    addressChildrenList.add(prent);
                }else {
                    addressPrentList.add(prent);
                }
            }
        }
        // 判断是否还需要循环
        if(addressChildrenList == null || addressChildrenList.isEmpty()){
            return;
        }
        // 继续赛选
        handleChildren(addressPrentList, addressChildrenList, menuAddressDTOS);
    }


}
