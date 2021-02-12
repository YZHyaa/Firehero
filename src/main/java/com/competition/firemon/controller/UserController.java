package com.competition.firemon.controller;

import com.competition.firemon.bean.Msg;
import com.competition.firemon.service.CodeService;
import com.competition.firemon.service.UserService;
import com.competition.firemon.utils.JsonUtils;
import com.competition.firemon.utils.ValMail;
import com.competition.firemon.utils.ValPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    CodeService codeService;

    //未登录时找回密码
    @PostMapping("/fpwd")
    public Msg findPwd(@RequestBody String json){
        String email = JsonUtils.getString(json, "email");
        String code = JsonUtils.getString(json, "code");
        String password = JsonUtils.getString(json, "password");

        //校验参数是否为空
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(code) || StringUtils.isEmpty(password))
            return Msg.fail().add("error","参数不能为空");

        //验证邮箱合法性
        if(!ValMail.emailFormat(email))return Msg.fail().add("error","邮箱不合法，请重新输入");

        //校验验证码
        if(!codeService.checkEmailCode(email,code))return Msg.fail().add("error","验证码错误");

        //执行修改方法
        if(!userService.findPwd(email,code,password))return Msg.fail().add("error","该用户不存在");

        return Msg.success();
    }

    //登录后修改密码
    @PutMapping("/upwd")
    public Msg updatePwd(@RequestBody String json){
        String oldPwd = JsonUtils.getString(json, "oldPwd");
        String phone = JsonUtils.getString(json, "phone");
        String code = JsonUtils.getString(json, "code");
        String newPwd = JsonUtils.getString(json, "newPwd");

        if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(code) || StringUtils.isEmpty(newPwd))
            return Msg.fail().add("error","信息不能为空");

        //校验电话合法性
        if(!ValPhone.isCellPhoneNo(phone))return Msg.fail().add("error","请输入正确的电话号码");

        //校验验证码
        if(!codeService.checkPhoneCode(phone,code))return Msg.fail().add("error","验证码错误");

        //执行修改方法
        if(!userService.updatePwd(phone,oldPwd,newPwd))return Msg.fail().add("error","修改失败");

        return Msg.success();
    }

}
