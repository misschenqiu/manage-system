package com.starda.managesystem.common;

import cn.hutool.core.bean.BeanUtil;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.dto.MenusToRoleInfoDTO;
import com.starda.managesystem.pojo.dto.RoleInfoListDTO;
import com.starda.managesystem.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.common
 * @ClassName: CommonConfigura
 * @Author: chenqiu
 * @Description: 启动时 加载配置文件
 * @Date: 2021/8/22 22:08
 * @Version: 1.0
 */

@Configuration
@Component
public class CommonConfigura {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 全部权限
     */
    public List<MenusToRoleInfoDTO> menuList;

    /**
     * 获取全部资源 对应 角色
     */
    @PostConstruct
    public void getMenuInfoList(){
        // 所有路径
        List<SysMenu> menuList = this.sysMenuService.getMenuAllToRoleList();
        // 所有角色
        List<RoleInfoListDTO> roleToAllMenusList = this.sysMenuService.getRoleToAllMenusList();

        List<MenusToRoleInfoDTO> menusToRoleInfoDTOS = BeanUtil.copyToList(menuList, MenusToRoleInfoDTO.class);

        // 整理数据
        menusToRoleInfoDTOS.stream().forEach(menu ->{

            // 整理资源 角色数据
            List<RoleInfoListDTO> roleInfoListDTOList = new ArrayList<>();
            roleToAllMenusList.stream().forEach(roleMenu ->{
                if(null != roleMenu.getMenuIds() && roleMenu.getMenuIds().contains(menu.getId())){
                    roleInfoListDTOList.add(roleMenu);
                }
            });

            menu.setRoleList(roleInfoListDTOList);
        });

        setMenuList(menusToRoleInfoDTOS);
    }

    public void setMenuList(List<MenusToRoleInfoDTO> menuList){
        this.menuList = menuList;
    }

    public List<MenusToRoleInfoDTO> getMenuList(){
        return this.menuList;
    }


    /**
     * 文件名
     */
    public static String getFileName(String fileName){
        String fileType = fileName.substring(fileName.indexOf("."));

        return System.currentTimeMillis() + fileType;
    }

}
