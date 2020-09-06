package com.zhang.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String date2String(Date date, String patt){
    //日期转换字符串
    SimpleDateFormat sdf = new SimpleDateFormat(patt);
    String fomat = sdf.format(date);
    return fomat;
    }
    public static Date string2Date(String str, String patt) throws ParseException {
        //字符串转换日期
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
