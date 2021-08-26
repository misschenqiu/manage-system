package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.role.RoleInsertPO;
import com.starda.managesystem.pojo.po.role.RoleSelectPO;
import com.starda.managesystem.pojo.vo.role.RoleListVO;
import com.starda.managesystem.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: RoleController
 * @Author: chenqiu
 * @Description: 角色信息 控制层
 * @Date: 2021/8/24 21:50
 * @Version: 1.0
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private ISysRoleService roleService;

    /**
     * 添加角色
     * @param userVO
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("insertRoleInfo")
    public Result insertRoleInfo(@AnnotationAuthor UserVO userVO, @RequestBody @Valid RoleInsertPO po) throws Exception{

        this.roleService.insertRole(userVO, po);

        return Result.success();
    }

    @PostMapping("/removeRoleInfo/{roleIds}")
    public Result removeRoleInfo(@AnnotationAuthor UserVO userVO, @PathVariable @NotBlank(message = "id不能为空") String roleId) throws Exception{

        List<String> roleList = new ArrayList<String>(Arrays.asList(roleId.split(",")));
        this.roleService.deleteRole(roleList.stream().map(data->Integer.valueOf(data)).collect(Collectors.toList()));

        return Result.success();
    }

    @PostMapping("getRoleInfoList")
    public Result getRoleInfoList (@AnnotationAuthor UserVO userVO, @RequestBody @Valid RoleSelectPO po) throws Exception{

        List<RoleListVO> roleListVOS = this.roleService.selectRoleList(userVO, po);

        return Result.success();
    }

}
