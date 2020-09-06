package com.yes.mysell.utils;

import com.yes.mysell.enums.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
         for (T each:enumClass.getEnumConstants()){
             if(each.getCode().equals(code)){
                 return each;
             }
         }
         return null;
    }
}
