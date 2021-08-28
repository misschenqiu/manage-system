package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: CompanyController
 * @Author: chenqiu
 * @Description: 合作单位信息
 * @Date: 2021/8/27 22:34
 * @Version: 1.0
 */

@RestController
@RequestMapping("/company")
public class CompanyController {

    /**
     * 获取单位信息列表
     * @return
     * @throws Exception
     */
    @PostMapping("/getCompanyInfoList")
    public Result getCompanyInfoList() throws Exception{

        return null;
    }

    /**
     * 添加担心合作单位信息
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/insertCompanyInfo")
    public Result insertCompanyInfo(@AnnotationAuthor UserVO userVO) throws Exception{

        return Result.success();
    }

    /**
     * 删除合作单位
     * @param userVO
     * @param companyIds 单位id 多个用逗号隔开
     * @return
     * @throws Exception
     */
    @PostMapping("/removeCompanyInfo/{companyIds}")
    public Result removeCompanyInfo(@AnnotationAuthor UserVO userVO, @PathVariable @NotBlank(message = "请选择删除单位")String companyIds) throws Exception{

        return Result.success();
    }

}
