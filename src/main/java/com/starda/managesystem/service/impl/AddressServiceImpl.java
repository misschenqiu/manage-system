package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.system.SysAddressMapper;
import com.starda.managesystem.mapper.system.SysMenuMapper;
import com.starda.managesystem.mapper.system.SysRoleMenuMapper;
import com.starda.managesystem.pojo.*;
import com.starda.managesystem.pojo.dto.MenuAddressDTO;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.pojo.vo.AddressVO;
import com.starda.managesystem.pojo.vo.MenuAddressVO;
import com.starda.managesystem.pojo.vo.role.MenuAddressListVO;
import com.starda.managesystem.service.IAddressService;
import com.starda.managesystem.util.BeanCopyUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

        int pId = 0;
        // 父级
        for (int i = 0; i < pNameList.size() - Constant.BaseNumberManage.ONE; i++) {
            try {
                if(i == pNameList.size() - Constant.BaseNumberManage.TWO) {
                    SysAddress address = new SysAddress();
                    address.setAddressName(pNameList.get(i));
                    address.setAddressUrl(addressUrlList.get(i));
                    address.setChildren(Constant.BaseStringInfoManage.CHILDREN_NO);
                    address.setMenuId(0);
                    address.setPId(pId);
                    address.setRoute(pathList.get(i));
                    address.setIcon(iconList.get(i));
                    this.insertSysAddress(address);
                    pId = address.getId();
                }else {
                    SysAddress address = new SysAddress();
                    address.setAddressName(pNameList.get(i));
                    address.setAddressUrl(addressUrlList.get(i));
                    address.setChildren(Constant.BaseStringInfoManage.CHILDREN_YES);
                    address.setPId(pId);
                    address.setRoute(pathList.get(i));
                    address.setIcon(iconList.get(i));
                    this.insertSysAddress(address);
                    pId = address.getId();
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new ManageStarException(ExceptionEnums.ADDRESS_URL.getCode(), ExceptionEnums.ADDRESS_URL.getMessage());
            }
        }
        // 路劲子级 如果没有后端路劲不添加
        if(StrUtil.isEmpty(po.getAddressUrl())){
            return;
        }
        SysMenu menu = new SysMenu();
        menu.setMenu_name(pNameList.get(pNameList.size() - Constant.BaseNumberManage.ONE));
        menu.setCode(po.getSmilName());
        menu.setCreate_time(new Date());
        menu.setUrl(po.getUrl());
        menu.setAddress_url(addressUrlList.get(addressUrlList.size() - Constant.BaseNumberManage.ONE));
        menu.setRemark(po.getRemark());
        menu.setP_id(pId);
        menu.setRoute(pathList.get(pathList.size() - Constant.BaseNumberManage.ONE));
        menu.setIcon(iconList.get(iconList.size() - Constant.BaseNumberManage.ONE));
        menuMapper.insertSelectiveMenu(menu);
        Integer menuId = menu.getId();
        log.info("添加权限，路径信息 menuId->{}" + menuId);
    }

    /**
     * 添加权限地址
     * @param address
     * @return
     * @throws Exception
     */
    private Integer insertSysAddress(SysAddress address) throws Exception{

        // 1.查阅是否已经添加
        LambdaQueryWrapper<SysAddress> wrapper = new LambdaQueryWrapper<SysAddress>();
        List<SysAddress> addressList = this.baseMapper.selectList(wrapper.eq(SysAddress::getAddressName, address.getAddressName())
                                                                        .eq(SysAddress::getAddressUrl, address.getAddressUrl())
                                                                        .eq(SysAddress::getRoute, address.getRoute()));

        if(null != addressList && !addressList.isEmpty()){
            return addressList.get(Constant.BaseNumberManage.ZERO).getId();
        }

        // 2.添加数据
        this.baseMapper.insertSelective(address);
        return address.getId();
    }

    @Override
    public List<MenuAddressListVO> getAddressList(UserVO vo) throws Exception {
        boolean flash = false;
        List<SysRole> roleList = new ArrayList<SysRole>();
        // 判断是列表还是 菜单
        if(StrUtil.isNotBlank(vo.getRoleListString())) {
            // 1. 获取改角色下的所有资源
            roleList = JSONArray.parseArray(vo.getRoleListString(), SysRole.class);
            log.info("角色信息是否转换成功-》" + roleList.size());
            // 权限数据
            List<SysRole> roleAdmin = roleList.stream().filter(param -> param.getRole_code().contains(Constant.BaseStringInfoManage.MANAGE)).collect(Collectors.toList());
            if(roleAdmin != null && !roleAdmin.isEmpty()){
                flash = true;
            }
        }
        List<MenuAddressDTO> menuAddressDTOS = new ArrayList<MenuAddressDTO>();
         if(flash){
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
        List<MenuAddressListVO> menuAddressVOList = this.handleAddress(addressList, menuAddressDTOS, StrUtil.isNotBlank(vo.getRoleListString()));

        return menuAddressVOList;
    }

    @Override
    public List<MenuAddressVO> getMenuAddressList(UserVO vo) throws Exception {

        // 1. 获取改角色下数据
        List<MenuAddressVO> menuAddressDTOS = BeanUtil.copyToList(this.getAddressList(vo), MenuAddressVO.class);

        log.info("获取到的角色下所有数据-》{}" + menuAddressDTOS.size());

        // 获取自己的权限
        // 判断是列表还是 菜单
        if(StrUtil.isBlank(vo.getRoleListString())) {
            // 1. 没有权限
          return new ArrayList<MenuAddressVO>();
        }
        List<SysRole> roleList = JSONArray.parseArray(vo.getRoleListString(), SysRole.class);
        List<Integer> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());

        log.info("拥有角色" + roleIds);
        List<SysAddress> addressList = this.baseMapper.selectJoinList(SysAddress.class, new MPJLambdaWrapper<SysAddress>()
                .selectAll(SysAddress.class)
                .rightJoin(SysRoleMenu.class, SysRoleMenu::getMenu_id, SysAddress::getId)
                .in(SysRoleMenu::getRole_id, roleIds));
        log.info("拥有的权限" + addressList);
        if (null == addressList || addressList.isEmpty()){
            return new ArrayList<MenuAddressVO>();
        }

        List<Integer> list = addressList.stream().map(SysAddress::getId).collect(Collectors.toList());

        return menuAddressDTOS.stream()
                .filter(addressId -> {
                    if (null == addressId.getChildren() || addressId.getChildren().isEmpty()){
                        if(list.contains(addressId.getId())){
                            return false;
                        }
                    }
                    List<MenuAddressListVO> newChild = new ArrayList<MenuAddressListVO>();
                    for (MenuAddressListVO child : addressId.getChildren()) {
                        if(list.contains(child.getId())){
                            newChild.add(child);
                        }
                    }
                    // 子类
                    addressId.getChildren().clear();
                    addressId.setChildren(newChild);
                    return true;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<MenuAddressListVO> getMenuAddressChoiceList(UserVO vo) throws Exception {

        // 1. 获取改角色下数据
        vo.setRoleListString(null);
        List<MenuAddressListVO> menuAddressVOList = this.getAddressList(vo);
        log.info("获取到的角色下所有数据-》{}" + menuAddressVOList.size());
        // 返回数据
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
    public String addManageAddress(String addressName) throws Exception {
        // 检查是否又数据
        AddressVO one = this.baseMapper.getManageAddress(addressName);
        if(one != null){
            return one.getAddressCode();
        }
        // 添加数据
        ManageAddress address = new ManageAddress();
        String code = UUID.fastUUID().toString().replaceAll("-", "");
        address.setAddressName(addressName);
        address.setAddressCode(code);
        address.setStatus(Constant.BaseStringInfoManage.CHILDREN_YES);

        this.baseMapper.insertAddress(address);
        return code;
    }

    @Override
    public List<AddressVO> getManageAddress() throws Exception {

        List<AddressVO> addressVOIPage = this.baseMapper.getAddressList();

        return addressVOIPage;
    }

    @Override
    public void removeManageAddress(Integer addressId) throws Exception {

        this.baseMapper.updateAddress(addressId);

    }

    @Override
    public List<SysAddress> getMPJSysAddressList(MPJLambdaWrapper<SysAddress> mapper) throws Exception {
        return this.baseMapper.selectJoinList(SysAddress.class, mapper);
    }

    /**
     * 便利 处理子父级
     * @param addressList 父级
     * @param menuAddressDTOS 子级
     * @param flash  判断是否是 权限列表 ture 是查询 查单 flash 权限列表
     */
    private List<MenuAddressListVO> handleAddress(List<SysAddress> addressList, List<MenuAddressDTO> menuAddressDTOS, boolean flash){

        boolean children = false;
        // 父级 id
        Set<Integer> pid = new HashSet<Integer>();

        // 转换数据
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("urlName", "name");
        mapping.put("menuId", "sort");
        mapping.put("icon", "icons");
        mapping.put("addressUrl", "component");
        mapping.put("route", "path");
        List<MenuAddressListVO> menuAddressVOList = BeanCopyUtil.copyToList(addressList, MenuAddressListVO.class, mapping);

        // 解析对象
        menuAddressVOList.stream().forEach(menu->{
            menu.setMeta(JSONObject.parseObject(menu.getIcons(), HashMap.class));
        });

        // 取出子级
        if(menuAddressDTOS == null || menuAddressDTOS.isEmpty() || menuAddressDTOS.size() < 1){
            children = true;
        }else {
            Map<Integer, List<MenuAddressDTO>> collect = menuAddressDTOS.stream().collect(Collectors.groupingBy(MenuAddressDTO::getPId));
            pid = collect.keySet();
            // 赋值详情数据
            if(!flash){
                menuAddressVOList.stream().forEach(menu->{
                    menu.setAddressInfo(collect.get(menu.getId()));
                });
                children = true;
            }
        }
        
        // 子级数据
        List<MenuAddressListVO> addressListPrent = menuAddressVOList.stream().filter(menu->menu.getPId().equals(Constant.BaseNumberManage.ZERO)).collect(Collectors.toList());
        List<MenuAddressListVO> addressListChildren = menuAddressVOList.stream().filter(menu->!menu.getPId().equals(Constant.BaseNumberManage.ZERO)).collect(Collectors.toList());

        // 处理父级数据，前端页面显示
        this.handleChildren(addressListPrent, addressListChildren);
        
        // 判断是否有权限
        if(children){
            return addressListPrent;
        }
        
        // 处理展现数据
        this.handleDataShow(pid, addressListPrent);

        return addressListPrent;
    }

    /**
     * 处理迭代数据
     * @param addressListPrent
     * @param addressListChildren
     */
    private void handleChildren(List<MenuAddressListVO> addressListPrent, List<MenuAddressListVO> addressListChildren){

        // 取出子级
        for (MenuAddressListVO prent : addressListPrent) {
            // 子级数据
            List<MenuAddressListVO> addressPrentList = new ArrayList<MenuAddressListVO>();
            List<MenuAddressListVO> addressChildrenList = new ArrayList<MenuAddressListVO>();

            for (MenuAddressListVO children : addressListChildren) {
                if(prent.getId().equals(children.getPId())){
                    addressPrentList.add(children);
                }else {
                    addressChildrenList.add(prent);
                }
            }
            // 判断是否还需要循环
            if(addressChildrenList == null || addressChildrenList.isEmpty()){
                return;
            }
            // 赛选剩余的数据
            this.handleChildren(addressPrentList, addressChildrenList);
            // 赛选完了，填入子类
            prent.setChildren(addressPrentList);
        }

    }

    /**
     * 处理展示数据
     * @param pid 子级父级id TODO 待完成 菜单目录展示
     * @param menuAddressVOList
     */
    public void handleDataShow(Set<Integer> pid, List<MenuAddressListVO> menuAddressVOList){
        List<MenuAddressListVO> menuAddressVOChildren = new ArrayList<MenuAddressListVO>();
        // 赛选父级数据
        for (MenuAddressListVO menuAddressVO : menuAddressVOList) {
            if(menuAddressVO.getChildren() != null && !menuAddressVO.getChildren().isEmpty()){
                handleDataShow(pid, menuAddressVO.getChildren());
            }
            if(pid.contains(menuAddressVO.getId())){
                menuAddressVOChildren.add(menuAddressVO);
            }
        }

        // 最终权限
        menuAddressVOList.clear();
        menuAddressVOList.addAll(menuAddressVOChildren);

    }

}
