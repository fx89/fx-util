package com.desolatetimelines.utils.lang;

public class StringUtils {
    public static Object nvl(Object str1, Object str2) {
        return str1 == null ? str2 : str1;
    }
}
