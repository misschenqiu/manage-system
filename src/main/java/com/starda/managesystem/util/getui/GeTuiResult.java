package com.starda.managesystem.util.getui;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util.getui
 * @ClassName: GeTuiResult
 * @Author: chenqiu
 * @Description: 个推返回数据
 * @Date: 2021/9/22 9:23
 * @Version: 1.0
 */

@Data
public class GeTuiResult {

    private Integer code;

    private String msg;

    private DataResult data;

}

@Data
class DataResult{
    private String expire_time;
    private String token;
}