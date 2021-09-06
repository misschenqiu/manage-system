package com.starda.managesystem.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.enums
 * @ClassName: FinishTimeEnums
 * @Author: chenqiu
 * @Description: 任务状态信息
 * @Date: 2021/9/6 23:14
 * @Version: 1.0
 *
 * 0.未结束，1.终止  2. 未到款  3.完成 4.退回 5.驳回
 *
 */

public enum FinishTimeEnums {

    NO_FINISH(0, "未结束"),

    STOP(1, "终止"),

    NO_BACK_MONEY(2, "未到款"),

    FINISH(3, "完成"),

    BACK_TASK(4, "退回"),

    REJSCT_TASK(5, "驳回");

    /**
     * 标记数据库 字段
     */
    private int status;

    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    FinishTimeEnums(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static String getMessage(Integer status){
        FinishTimeEnums[] enums = FinishTimeEnums.values();
        for (int i = 0; i < enums.length; i++) {
            if(status.equals(enums[i].getStatus())){
                return enums[i].getMessage();
            }
        }
        return "";
    }

}
