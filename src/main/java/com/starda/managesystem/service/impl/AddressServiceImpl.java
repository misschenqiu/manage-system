package com.starda.managesystem.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.system.SysAddressMapper;
import com.starda.managesystem.mapper.system.SysMenuMapper;
import com.starda.managesystem.pojo.SysAddress;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.pojo.vo.AddressVO;
import com.starda.managesystem.service.IAddressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        SysMenu menu = SysMenu.builder().menu_name(pNameList.get(pNameList.size() - Constant.BaseNumberManage.ONE))
                                        .code(po.getSmilName())
                                        .create_time(new Date())
                                        .url(po.getUrl())
                                        .addressUrl(addressUrlList.get(addressUrlList.size() - Constant.BaseNumberManage.ONE))
                                        .build();
        Integer menuId = menuMapper.insertSelective(menu);
        log.info("添加权限，路径信息 menuId->{}" + menuId);
        int pId = 0;
        // 父级
        for (int i = 0; i < pNameList.size() - Constant.BaseNumberManage.ONE; i++) {
            try {
                if(i == pNameList.size() - Constant.BaseNumberManage.TWO) {
                    SysAddress address = SysAddress.builder()
                            .addressName(pNameList.get(i))
                            .addressUrl(addressUrlList.get(i))
                            .children(Constant.BaseStringInfoManage.CHILDREN_NO)
                            .menuId(menuId)
                            .pId(pId)
                            .build();
                    pId = this.baseMapper.insertSelective(address);
                }else {
                    SysAddress address = SysAddress.builder()
                            .addressName(pNameList.get(i))
                            .addressUrl(addressUrlList.get(i))
                            .children(Constant.BaseStringInfoManage.CHILDREN_YES)
                            .pId(pId)
                            .build();
                    pId = this.baseMapper.insertSelective(address);
                }
            }catch (Exception e){
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
    public void addManageAddress(String addressName) throws Exception {

    }

    @Override
    public Page<AddressVO> getManageAddress(Integer currentPage, Integer pageSize) throws Exception {
        return null;
    }

    @Override
    public void removeManageAddress(Integer addressId) throws Exception {

    }
}
