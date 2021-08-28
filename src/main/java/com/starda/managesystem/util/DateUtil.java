package com.starda.managesystem.util;

import lombok.extern.log4j.Log4j2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: DateUtil
 * @Author: chenqiu
 * @Description: 时间工具类
 * @Date: 2021/8/28 0:29
 * @Version: 1.0
 */

@Log4j2
public class DateUtil {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化时间
     */
    private static String formatTime(Date time){
        String format = DateUtil.format.format(time);
        return format;
    }

}
