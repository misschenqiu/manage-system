package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: AddressController
 * @Author: chenqiu
 * @Description: 添加 路劲实例类
 * @Date: 2021/8/23 23:44
 * @Version: 1.0
 */

@RestController
@RequestMapping("/address/inner")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    /**
     * 添加 路由地址
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("addAddressInfo")
    public Result addAddressInfo(@RequestBody @Valid AddressUrlPO po) throws Exception{

        this.addressService.insertAddress(po);

        return Result.success();
    }

    /**
     * 删除路由
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("remove/{id}")
    public Result removeAddress(@PathVariable("id") Integer id) throws Exception{

        return null;
    }

}
