package com.sakuradon.mahoutsukai.utils;

/**
 * @author SakuraDon
 */
public class StringUtil {

    public static boolean isBlank(String s) {
        return s == null | "".equals(s);
    }

}
