package com.taomei.service.share.utils;

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

}
