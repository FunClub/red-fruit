package com.taomei.service.share.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间生成类
 */
public class TimeUtil {

    /**
     *  生成文件名字所用时间
     * @return
     */
    public static String getFileTime() {
        Date date = new Date();
        return generateTime("yyyyMMddHHmmss")+date.hashCode();
    }
    public static String generateTime(String format){
        Date date = new Date();
        SimpleDateFormat formats = new SimpleDateFormat(format);
        return formats.format(date);
    }
    public static String getSimpleTime(){
        return generateTime("yyyy-M-d HH:mm:ss");
    }

    /**
     * 获得现在多久的时间
     * @param time 待计算的时间
     * @return  距离现在在多久的时间
     */
    public static String calculateHowLongAgo(String time) {
        Date calculateTime = null;
        Date nowTime = null;
        try {
            calculateTime = new SimpleDateFormat("yyyy-M-d HH:mm:ss")
                    .parse(time);
            nowTime = new SimpleDateFormat("yyyy-M-d HH:mm:ss")
                    .parse(getSimpleTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int second = (int) ((nowTime.getTime() - calculateTime.getTime()) / 1000);
        int minutes = second / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int months = days / 30;
        int years = days / 365;
        if (minutes == 0) {
            return second + "秒";
        } else if (hours == 0) {
            return minutes + "分钟";
        } else if (days == 0) {
            return hours + "小时";
        } else if (months == 0) {
            return days + "天";
        } else if (years == 0) {
            return months + "个月";
        } else {
            return years + "年";
        }

    }

    /**
     * 获取分门别类的时间
     * @param date 日期
     * @return 分门别类的时间
     */
    public static String  calculateSortDate(String date){
        String howLongAgo= calculateHowLongAgo(date);
        int howLongLength = howLongAgo.length();
        int dateLength = date.length();
        char lastHowLong = howLongAgo.charAt(howLongLength-1);
        char firstHowLong = howLongAgo.charAt(0);
        //截取年月日
        StringBuilder sb = new StringBuilder(date);
        int blackChar = sb.indexOf(" ");
        String yyd= sb.delete(blackChar,date.length()).toString();

        //截取月日
        String yd = yyd.substring(5,blackChar);
        if(lastHowLong=='年'){
            return yyd;
        }else if (lastHowLong=='月'){
            return yd;
        }else if (lastHowLong=='天'){
            if(howLongLength==2){
                if(firstHowLong=='1'){
                    return "昨天";
                }else if (firstHowLong=='2'){
                    return "前天";
                }
            }
            return yd;
        }else{
            return "今天";
        }

    }

    /**
     * 获取简短日期比如2017-12-12 12:23:15 的简短日期是12:23
     * @param date
     * @return
     */
    public static  String generateShortDate(String date){
        int blackChar = new StringBuilder(date).indexOf(" ");
        return date.substring(blackChar+1,date.length()-3);
    }

}
