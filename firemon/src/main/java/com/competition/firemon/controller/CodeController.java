package com.competition.firemon.controller;

import com.competition.firemon.bean.Msg;
import com.competition.firemon.service.CodeService;
import com.competition.firemon.utils.JsonUtils;
import com.competition.firemon.utils.ValPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//发送验证码
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;


    @PostMapping("/phone")
    public Msg sendPhoneCode(@RequestBody String json){
        String phone = JsonUtils.getString(json, "phone");
        //验证手机号合法性和发送验证码
        if (!ValPhone.isCellPhoneNo(phone) || !this.codeService.sendPhoneCode(phone)) {
            return Msg.fail().add("error","手机验证码发送失败");
        }

        return Msg.success();
    }

    @PostMapping("/email")
    public Msg sendEmailCode(@RequestBody String json) {
        String email = JsonUtils.getString(json, "email");
        if (!this.codeService.sendEmailCode(email)) {
            return Msg.fail().add("error","邮箱验证码发送失败");
        }
        return Msg.success();
    }

}
