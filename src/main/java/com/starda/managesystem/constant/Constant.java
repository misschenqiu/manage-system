package com.starda.managesystem.constant;

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
        // 本地缓存最大数
        long NUMBER = 1000;
        long TIME_CACHE = 12 * 60;
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
        String BASE_FILE_PATH = "/file/img/";
        String BASE_HEADER_PATH = "/header/img/";
    }

}
