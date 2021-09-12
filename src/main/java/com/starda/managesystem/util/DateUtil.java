package com.starda.managesystem.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private static SimpleDateFormat formatYear = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化时间
     */
    private static String formatTime(Date time){
        String format = DateUtil.formatYear.format(time);
        return format;
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 开始时间
     * @return
     */
    public static String getStartDate(Date strDate){
        return  format.format(strDate)+" 00:00:00";
    }
    /**
     * 结束时间
     * @return
     */
    public static String getEndDate(Date strDate){
        Calendar c = Calendar.getInstance();
        c.setTime(strDate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return  format.format(strDate.getTime())+" 23:59:59";
    }

    /**
     * 前一天的开始时间
     * @return
     */
    public static String getYesToDayDate(){
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -1);
        Date date = ca.getTime();
        return format.format(date)+" 00:00:00";
    }

    /**
     * 前一天的开始时间
     * @return
     */
    public static String getYesToDayEndDate(){
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -1);
        Date date = ca.getTime();
        return format.format(date)+" 23:59:59";
    }

    /**
     * 当前月的第一天
     * @return
     */
    public static String getStartTime(){
        LocalDate today = LocalDate.now();
        LocalDate firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1);

        LocalDateTime startTime = LocalDateTime.of(firstDay, LocalTime.MIN);

        return startTime.toLocalDate()+" 00:00:00";
    }

    /**
     * 当前月的最后一天
     * @return
     */
    public static String getEndTime(){
        LocalDate today = LocalDate.now();
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());

        LocalDateTime endTime = LocalDateTime.of(lastDay, LocalTime.MAX);
        return endTime.toLocalDate()+" 23:59:59";
    }

    /**
     * 获取当前周的第一天
     * @return
     */
    public static String getWeekStartDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);

        return weekBegin+" 00:00:00";
    }

    /**
     * 获取当前周的最后一天
     * @return
     */
    public static String getWeekEndDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, 6 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);

        return weekEnd+" 23:59:59";
    }

    /**
     * 获取当前年第一天
     */
    public static String getYearStartTime(){
        return LocalDate.now().getYear()+"-01-01 00:00:00";
    }

    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime())+" 23:59:59";
    }

    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getEndMonthDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = countDays(year, month);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime())+" 00:00:00";
    }

    /**
     * 获取指定年月的第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime())+" 00:00:00";
    }

    /**
     * 当前日的开始时候
     * @return
     */
    public static String getToDayStartTime(){
        return LocalDate.now()+" 00:00:00";
    }

    /**
     * 当前日的结束时候
     * @return
     */
    public static String getToDayEndTime(){
        return LocalDate.now()+" 23:59:59";
    }

    /**
     * 获取指定时间 之间的 日期
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getStartAndEndDays(String startTime, String endTime) {
        if(StringUtils.isBlank(endTime)){
            endTime = LocalDateTime.now().toString();
        }

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date end = dateFormat.parse(endTime);
            // 如何开始时间为空直接取当前时间
            if(StringUtils.isBlank(startTime)){
                Calendar tempEnd = Calendar.getInstance();
                tempEnd.setTime(end);
                days.add(dateFormat.format(tempEnd.getTime()));
                return days;
            }
            Date start = dateFormat.parse(startTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // 日期加1(包含结束)
            tempEnd.add(Calendar.DATE, +1);
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return days;
    }

    /**
     * 指定时间的判断
     * @param startTime 时间
     *
     *  2021年10月31号以及之前：LOG_BUSINESS_20201031
     * 20201101至2021年7月15日以及之前：LOG_BUSINESS_20210715
     * 2021年7月16日之后：LOG_BUSINESS
     *
     * @return
     */
    public static Integer isTimeTempy(String startTime){

        try {
            Date time = format.parse(startTime);
            // 2021.07.16
            if(time.getTime()<=format.parse("2021-07-14 00:10:00").getTime()){
                return 1;
            }
            // 2021.10.31
            if(time.getTime()<=format.parse("2021-10-31 00:10:00").getTime()){
                return 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 3;
    }

    /**
     * 获取某月的最后一天
     *
     */
    public static int getMonthOfLastDay(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return Integer.valueOf(lastDayOfMonth);
    }

    /**
     * get appoint start time
     * @param startTime
     * @return
     */
    public static String getAppointStartTime(String startTime){
        if (startTime.contains(":")){
            return startTime;
        }
        return startTime + " 00:00:00";
    }

    /**
     * get appoint end time
     * @param endTime
     * @return
     */
    public static String getAppointEndTime(String endTime){
        if (endTime.contains(":")){
            return endTime;
        }
        return endTime + " 23:59:59";
    }

    /**
     * 获取天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int countDays(int year, int month) {
        int count = -1;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                count = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                count = 30;
                break;
            case 2:
                if (year % 4 == 0) {
                    count = 29;
                } else {
                    count = 28;
                }
                if ((year % 100 == 0) & (year % 400 != 0)) {
                    count = 28;
                }
        }
        return count;
    }

}
