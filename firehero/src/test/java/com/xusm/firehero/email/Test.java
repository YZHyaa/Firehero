package com.xusm.firehero.email;

public class Test {

    public static void main(String[] args) {
        try {
            MailUtil.sendMail("1327584236@qq.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
