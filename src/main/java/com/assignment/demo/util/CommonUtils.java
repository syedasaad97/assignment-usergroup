package com.assignment.demo.util;


public final class CommonUtils {

    private CommonUtils() {
    }

    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }


    public static boolean isStringNotNullEmpty(String str) {
        return ((str != null) && !str.trim().isEmpty());
    }
}
