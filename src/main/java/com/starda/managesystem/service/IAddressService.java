package com.starda.managesystem.service;

import com.starda.managesystem.pojo.po.address.AddressUrlPO;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IAddressService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/23 23:48
 * @Version: 1.0
 */
public interface IAddressService {

    /**
     * 添加路由
     * @param po 参数
     * @throws Exception
     */
    void insertAddress(AddressUrlPO po) throws Exception;

}
