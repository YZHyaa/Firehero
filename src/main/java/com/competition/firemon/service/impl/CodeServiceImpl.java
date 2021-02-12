package com.competition.firemon.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.exceptions.ClientException;
import com.competition.firemon.config.prop.SmsProperties;
import com.competition.firemon.service.CodeService;
import com.competition.firemon.utils.MailUtil;
import com.competition.firemon.utils.NumberUtils;
import com.competition.firemon.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@EnableConfigurationProperties(SmsProperties.class)
public class CodeServiceImpl implements CodeService {

    static final String KEY_PREFIX_PHONE = "code:phone:";
    static final String KEY_PREFIX_EMAIL = "code:email";

    static final Logger LOGGER = LoggerFactory.getLogger(CodeService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SmsUtils smsUtils;

    @Resource
    private SmsProperties smsProperties;


    @Override
    public boolean sendPhoneCode(String phone) {
        String code = NumberUtils.generateCode(6);
        this.redisTemplate.opsForValue().set(KEY_PREFIX_PHONE + phone,code,5, TimeUnit.MINUTES);
        try {
            this.smsUtils.sendSms(phone,code,smsProperties.getSignName(),smsProperties.getVerifyCodeTemplate());
        } catch (ClientException e) {
            LOGGER.error("发送短信失败："+phone+","+code);
            return false;
        }

        return true;
    }

    public boolean checkPhoneCode(String phone,String code){
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX_PHONE+phone);
        if(StringUtils.equals(code,cacheCode)){
            this.redisTemplate.delete(KEY_PREFIX_PHONE+phone);
            return true;
        }
        return false;
    }

    @Override
    public boolean sendEmailCode(String email) {
        String code = NumberUtils.generateCode(6);
        try {
            this.redisTemplate.opsForValue().set(KEY_PREFIX_EMAIL + email, code, 5, TimeUnit.MINUTES);
            MailUtil.sendMail(email, code);
        } catch (Exception e) {
            LOGGER.error("发送邮件失败："+ email +","+code);
            return false;
        }
        return true;
    }


    @Override
    public boolean checkEmailCode(String email, String code) {
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX_EMAIL + email);
        if(StringUtils.equals(code,cacheCode)){
            this.redisTemplate.delete(KEY_PREFIX_EMAIL + email);
            return true;
        }
        return false;
    }
}
