package com.liu.oa.framwork.utils;

import com.liu.oa.common.enums.CodeEmnu;

/**
 * 通过 code 获取 ，和EnumClass 来去获取 msg
 */
public class EnumUtil {

    public static <T extends CodeEmnu> T getByCode(Integer code,Class<T> enumClass){
        for (T eachEnum : enumClass.getEnumConstants()) {
           if(code.equals(eachEnum.getCode())){
             return  eachEnum;
           }
        }
        return null;
    }

}
