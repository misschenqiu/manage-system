package com.starda.managesystem.pojo.vo;

import com.starda.managesystem.pojo.vo.role.MenuAddressListVO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo
 * @ClassName: MenuAddressVO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/26 23:10
 * @Version: 1.0
 */

@Data
public class MenuAddressVO {

    private Integer id;

    /**
     * 路径名
     */
    private String title;

    /**
     * 路径地址
     */
    private String component;

    /**
     * 浏览器路径
     */
    private String path;

    /**
     * 父级ia
     */
    private Integer pId;

    /**
     * 包装名字
     */
    private String meta;

    /**
     * 前端自用
     */
    private String redirect;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子类
     */
    private List<MenuAddressListVO> children;

}
