package com.starda.managesystem.constant;

import java.io.File;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.constant
 * @ClassName: Constant
 * @Author: chenqiu
 * @Description: 常量池
 * @Date: 2021/8/5 14:47
 * @Version: 1.0
 */
public interface Constant {

    /**
     * 返回成功吗
     */
    interface ResultCodeMessage{
        String SUCCESS = "success";
        String FAIL = "fail";
        Boolean DEFAULT = true;
        Boolean ERROR = false;
    }

    /**
     * 基本系统字符串
     */
    interface BaseStringInfoManage{
        String COOKIES = "JSESSIONID";
        String SUCCESS = "登录成功";

        /**
         * 大管理员
         */
        String MANAGE = "admin";

        // 本地缓存最大数
        long NUMBER = 1000;
        long TIME_CACHE = 12 * 60 * 60;
        /**
         * 请求投中 特定参数
         */
        String HEADER = "Authorization";

        /**
         * 设置默认角色
         */
        String DEFAULT_ROLE = "ROLE_def";

        /**
         * 设置文件上传 基本路径
         */
        String BASE_FILE_PATH = File.separator + "file"+ File.separator +"img" + File.separator;
        String BASE_HEADER_PATH = File.separator + "header" + File.separator + "img" + File.separator;

        /**
         * 是否有子级  0.否 1.是
         */
        int CHILDREN_YES = 1;
        int CHILDREN_NO = 0;

        /**
         * 角色基本常量
         */
        String DEFT = "ROLE_";
    }

    /**
     * 基本数字管理
     */
    interface BaseNumberManage{
        Integer ONE = 1;
        Integer TWO = 2;
        Integer THREE = 3;
        Integer FOUR = 4;
        Integer FIVE = 5;
        Integer SIX = 6;
        Integer SEVEN = 7;
        Integer EIGHT = 8;
        Integer NINE = 9;
        Integer TEN = 10;
        Integer ZERO = 0;
    }

    /**
     * 文件类型数据
     */
    interface BaseFileType{
        // 头像
        int ONE = 1;
        // 工作照
        int TWO = 2;
    }

    /**
     * 消息提醒是否修改
     * 1.开启 2.删除 3.重复提醒
     */
    interface ReminderType{
        // 开启
        int UPDATE_REMINDER = 1;
        // 删除
        int DELETE_REMINDER = 2;
        // 重提提醒
        int UPDATE_AGAIN = 3;
    }

}
