package com.yes.mysell.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000)+100000;//生成六位随机数
        return  System.currentTimeMillis()+String.valueOf(number);//在多线程并发的时候随机数还是有可能重复的，所以在方法加关键词synchronized
    }


}
