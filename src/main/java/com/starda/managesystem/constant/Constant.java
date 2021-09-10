package com.starda.managesystem.constant;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String BASE_FILE_PATH = File.separator + "img"+ File.separator + "file"+ File.separator;
        String BASE_HEADER_PATH = File.separator + "img"+ File.separator + "header" + File.separator;

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

    /**
     * 详情状态 信息 0.未结束，1.终止  2. 未到款  3.完成 4.退回 5.驳回
     */
    interface TaskBusinessType{
        int ZERO = 0;
        int ONE = 1;
        int TWE = 2;
        int THREE = 3;
        int FOUR = 4;
        int FIVE = 5;
        List<Integer> CAN_UPDATE_AND_UPDATE = new ArrayList<Integer>(Arrays.asList(new Integer[]{3,4,5}));
    }

    /**
     * 确认下发0.否 1.是
     */
    interface ConfirmTaskType{
        int ONE = 1;
        int ZERO = 0;
    }

    /**
     * 查看类型1.待完成 2.已完成
     */
    interface AppQueryType{
        int ONE = 1;
        int TWO = 2;
    }

    /**
     * 人员类型 1.员工，2.管理员
     */
    interface  PeopleType{
        int STAFF = 1;
        int MANAGE = 2;
    }

    /**
     * 员工是否已经提交 0.否 1.是
     */
    interface StaffSubmitType{
        int SUBMIT_YES = 1;
        int SUBMIT_NO = 0;
    }

}
