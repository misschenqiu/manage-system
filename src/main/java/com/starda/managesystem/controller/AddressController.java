package com.starda.managesystem.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.pojo.po.address.AddressUrlPO;
import com.starda.managesystem.pojo.vo.AddressVO;
import com.starda.managesystem.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PostMapping("remove/{id}/{children}")
    public Result removeAddress(@PathVariable("id") Integer id, @PathVariable("children")Integer children) throws Exception{

        this.addressService.removeMenuAddress(id, children);

        return Result.success();
    }

    /**
     * 添加选择地址
     * @param addressName
     * @return
     * @throws Exception
     */
    @PostMapping("address/{addressName}")
    public Result insertManageAddress(@PathVariable("addressName")String addressName) throws Exception{

        if(StrUtil.isBlank(addressName)){
            throw new ManageStarException(ExceptionEnums.PARAM_NOT_COMPLETE.getCode(), ExceptionEnums.PARAM_NOT_COMPLETE.getMessage());
        }

        this.addressService.addManageAddress(addressName);

        return Result.success();
    }

    /**
     * 获取到管理地址
     * @param currentPage 显示条数
     * @param pageSize 页码
     * @return
     * @throws Exception
     */
    @PostMapping("getAddress/{currentPage}/{pageSize}")
    public Result getManageAddress(@PathVariable("currentPage")Integer currentPage, @PathVariable("pageSize")Integer pageSize) throws Exception{

        if(currentPage == null || pageSize == null){
            throw new ManageStarException(ExceptionEnums.PARAM_NOT_COMPLETE.getCode(), ExceptionEnums.PARAM_NOT_COMPLETE.getMessage());
        }

        Page<AddressVO> pageData = this.addressService.getManageAddress(currentPage, pageSize);

        Result<AddressVO> result = new Result<AddressVO>();
        result.resultPage(pageData.getRecords(), pageData.getSize(), pageData.getPages());

        return result;
    }

    /**
     * 添加选择地址
     * @param addressId
     * @return
     * @throws Exception
     */
    @PostMapping("removeManageAddress/{addressId}")
    public Result removeManageAddress(@PathVariable("addressId")Integer addressId) throws Exception{

        if(addressId == null || addressId < 0){
            throw new ManageStarException(ExceptionEnums.PARAM_NOT_COMPLETE.getCode(), ExceptionEnums.PARAM_NOT_COMPLETE.getMessage());
        }

        this.addressService.removeManageAddress(addressId);

        return Result.success();
    }

}
