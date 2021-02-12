package com.xusm.firehero.service;

public interface CodeService {
    boolean sendPhoneCode(String phone);

    boolean sendEmailCode(String email);

    boolean checkPhoneCode(String phone, String code);

    boolean checkEmailCode(String email, String code);
}
