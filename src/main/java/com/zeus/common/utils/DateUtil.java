package com.zeus.common.utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class DateUtil {

    public static final String DATE_WITH_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 秒转时间(yyyy-MM-dd HH:mm:ss)
     */
    public static String convertToDateFromTimeSeconds(Long currentTimeSeconds, String format) {
        SimpleDateFormat dateformat = new SimpleDateFormat(format);
        GregorianCalendar gc = new GregorianCalendar();
        // 秒 -> 毫秒
        gc.setTimeInMillis(1478921171L * 1000);
        return dateformat.format(gc.getTime());
    }

}
