package com.starda.managesystem.pojo.po.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.address
 * @ClassName: AddressUrlPO
 * @Author: chenqiu
 * @Description: 添加地址（路由） 实例
 * @Date: 2021/8/23 23:52
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUrlPO implements Serializable {

    private static final long serializableUUid = 1L;

    /**
     * 菜单名称 多级用”/“分割 父级在上成
     */
    @NotBlank(message = "菜单名称不能为空")
    @JsonProperty("pName")
    private String pName;

    /**
     * 最小子级简称 ( 用于角色权限配置时展示 )
     */
    @NotBlank(message = "子级菜单检称，不能为空")
    private String smilName;

    /**
     * 菜单路由 多级用”/“分割 父级在上成
     */
    @NotBlank(message = "菜单路由 多级用”,“分割 父级在上成")
    private String addressUrl;

    /**
     * 后端路径
     */
    @NotBlank(message = "后端路径不能为空")
    private String url;

    /**
     * 后端路径描述
     */
    @NotBlank(message = "后端路径描述")
    private String remark;
}
