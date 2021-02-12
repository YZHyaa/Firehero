package com.competition.firemon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValPhone {

    public static void main(String[] args) {
        System.out.println(isCellPhoneNo(""));
    }

    /**
     * 验证是否是正确合法的手机号码,只支持中国大陆电话号码
     *
     * @param telephone
     *            需要验证的打手机号码
     * @return 合法返回true，不合法返回false
     * */
    public static boolean isCellPhoneNo(String telephone) {
        if (telephone == null || "".equals(telephone)) {
            return false;
        }
        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);

        if (matcher.matches()) {
            return true;
        }
        return false;

    }

}
