package com.taomei.service.utils;

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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date)+date.hashCode();
    }
}
