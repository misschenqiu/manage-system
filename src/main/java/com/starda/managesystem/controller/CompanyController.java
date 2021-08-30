package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.company.CompanyInsertPO;
import com.starda.managesystem.pojo.po.company.CompanyQueryPO;
import com.starda.managesystem.pojo.po.company.CompanyUpdatePO;
import com.starda.managesystem.pojo.vo.company.CompanyListVO;
import com.starda.managesystem.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ICompanyService companyService;

    /**
     * 获取单位信息列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getCompanyInfoList")
    public Result getCompanyInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid CompanyQueryPO po) throws Exception {

        Result result = this.companyService.getCompanyInfoList(user, po);

        return result;
    }

    /**
     * 修改单位信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/UpdateCompanyInfoList")
    public Result UpdateCompanyInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid CompanyUpdatePO po) throws Exception {

        this.companyService.updateCompanyInfo(user, po);

        return Result.success();
    }

    /**
     * 添加担心合作单位信息
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/insertCompanyInfo")
    public Result insertCompanyInfo(@AnnotationAuthor UserVO userVO, @RequestBody @Valid CompanyInsertPO po) throws Exception {

        this.companyService.insertCompanyInfo(userVO, po);

        return Result.success();
    }

    /**
     * 删除合作单位
     *
     * @param userVO
     * @param companyIds 单位id 多个用逗号隔开
     * @return
     * @throws Exception
     */
    @PostMapping("/removeCompanyInfo/{companyIds}")
    public Result removeCompanyInfo(@AnnotationAuthor UserVO userVO, @PathVariable @NotBlank(message = "请选择删除单位") String companyIds) throws Exception {

        List<String> companyIdList = new ArrayList<String>(Arrays.asList(companyIds.split(",")));
        this.companyService.removeCompanyInfo(userVO, companyIdList.stream().map(companyId -> Integer.valueOf(companyId)).collect(Collectors.toList()));

        return Result.success();
    }

    /**
     * 单位详情
     * @param user
     * @param companyId
     * @return
     * @throws Exception
     */
    @PostMapping("getCompanyInfo/{companyId}")
    public Result getCompanyInfo(@AnnotationAuthor UserVO user, @PathVariable @NotNull Integer companyId) throws Exception{

        CompanyListVO resultData = this.companyService.getCompanyInfo(user, companyId);

        return Result.ok().resultPage(resultData);
    }

}
