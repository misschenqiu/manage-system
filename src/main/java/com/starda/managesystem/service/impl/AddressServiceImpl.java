package com.starda.managesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.mapper.system.SysAddressMapper;
import com.starda.managesystem.pojo.SysAddress;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.service.IAddressService;
import org.springframework.stereotype.Service;

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
public class AddressServiceImpl extends ServiceImpl<SysAddressMapper, SysAddress> implements IAddressService {

    @Override
    public void insertAddress(AddressUrlPO po) throws Exception {
        // 先添加子级 在添加父级
    }
}
