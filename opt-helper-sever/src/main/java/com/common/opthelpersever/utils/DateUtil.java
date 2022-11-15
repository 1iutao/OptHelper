package com.common.opthelpersever.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 日期工具类
 **/
public class DateUtil {

    /**
     * 当前时间
     * @return now
     */
    public static String now() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return simpleDateFormat.format(now);
    }
}
