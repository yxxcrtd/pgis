package com.igoosd.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验
 */
public class RegexUtils {


    /**
     * 手机号正则验证
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        if(!StringUtils.isEmpty(phone)){
            Pattern pattern = Pattern.compile("^[1][0-9]{10}$");
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }
        return false;
    }


    /**
     * 是否为邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if(!StringUtils.isEmpty(email)){
            Pattern pattern = Pattern.compile("^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

}
