package com.gxc15090111.garageandroid.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/5 1:48
 */
public class ValidateUtil {

    /**
     * 通用判断
     * @param telNum
     * @return
     */
    public static boolean isMobiPhoneNumber(String telNum) {
        String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobiPhoneNumber("12345678901"));
        System.out.println(isMobiPhoneNumber("1334567890"));
        System.out.println(isMobiPhoneNumber("13345678901"));
        System.out.println(isMobiPhoneNumber("13545678901"));
        System.out.println(isMobiPhoneNumber("13745678901"));
        System.out.println(isMobiPhoneNumber("13845678901"));
    }
}
