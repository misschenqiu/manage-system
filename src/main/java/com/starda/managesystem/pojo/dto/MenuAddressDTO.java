package com.starda.managesystem.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo
 * @ClassName: MenuAddressVO
 * @Author: chenqiu
 * @Description: 权限路径
 * @Date: 2021/8/26 21:07
 * @Version: 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuAddressDTO {

    private Integer id;

    /**
     * 路径名
     */
    private String addressName;

    /**
     * 简称
     */
    private String smilName;

    /**
     * 路径地址
     */
    private String addressUrl;

    /**
     * 浏览器路径
     */
    private String path;

    /**
     * 父级ia
     */
    private Integer pId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 前端自用
     */
    private String redirect;

    /**
     * 描述
     */
    private String remark;

}
