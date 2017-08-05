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
}
