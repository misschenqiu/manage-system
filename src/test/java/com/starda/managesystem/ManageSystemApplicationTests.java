package com.starda.managesystem;

import com.starda.managesystem.mapper.UserInfoMapper;
import com.starda.managesystem.pojo.UserInfo;
import com.starda.managesystem.pojo.enums.UserTypeEnums;
import com.starda.managesystem.service.IAppManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManageSystemApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IAppManageService appManageService;

//    @Test
    void contextLoads() {
        UserInfo info = new UserInfo();
        info.setSex(UserTypeEnums.MAN);
        info.setUsername("胡傻冒");
        info.setPassword("131540");
        userInfoMapper.insert(info);

    }

//    @Test
    void selectUser(){
        System.out.println(userInfoMapper.selectById(2));
    }

    @Test
    void selectInfo() {
        try {
            appManageService.getTaskInfoList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
