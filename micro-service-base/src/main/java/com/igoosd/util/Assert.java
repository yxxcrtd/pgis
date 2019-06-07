package com.igoosd.util;

import com.igoosd.exception.StaticException;

public class Assert {

    public static void notNull(Object obj, String message) {
        if (null == obj) {
            throw new StaticException(message);
        }
    }

    public static void hasText(String str, String message) {
        if (null == str || str.trim().equals("")) {
            throw new StaticException(message);
        }
    }

    public static void isTrue(boolean flag, String message) {
        if (!flag) {
            throw new StaticException(message);
        }
    }


}
