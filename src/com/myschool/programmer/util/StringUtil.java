package com.myschool.programmer.util;

/**
 * @Author: 86176
 * @Date: 2020/6/11 19:23
 * @Description:
 * String类的一些公用操作方法
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str))return true;
        return false;
    }
}
