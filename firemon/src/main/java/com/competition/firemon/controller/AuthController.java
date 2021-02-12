package com.competition.firemon.controller;

import cn.hutool.crypto.SecureUtil;
import com.competition.firemon.bean.Msg;
import com.competition.firemon.bean.User;
import com.competition.firemon.service.CodeService;
import com.competition.firemon.service.UserService;
import com.competition.firemon.utils.JsonUtils;
import com.competition.firemon.utils.ValidationUtil;
import com.competition.firemon.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    CodeService codeService;

    //登陆
    @PostMapping("/login")
    public Msg login(@RequestBody String json){
        String account = JsonUtils.getString(json, "account");
        System.out.println(account);
        String password = JsonUtils.getString(json, "password");

        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password))return Msg.fail().add("error","用户名或密码为空");

        //用到shiro的验证
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                return Msg.fail().add("error","用户不存在");
            } else if (e instanceof LockedAccountException) {
                return Msg.fail().add("error","用户被禁用");
            } else if (e instanceof IncorrectCredentialsException) {
                return Msg.fail().add("error","密码错误");
            } else {
                return Msg.fail().add("error","用户认证失败");
            }
        }

        User profile = (User) SecurityUtils.getSubject().getSession().getAttribute("profile");
        //给前端发送sessionID
        return Msg.success().add("token",SecurityUtils.getSubject().getSession().getId()).add("user",profile);
    }

    //注册
    @PostMapping("/register")
    public Msg register(@RequestBody UserVo userVo){
        User user = new User(null,userVo.getUsername(),userVo.getPhone(),userVo.getEmail(),userVo.getPassword());

        //二次校验
        ValidationUtil.ValidResult result = ValidationUtil.validateBean(user);

        if(result.hasErrors())return Msg.fail().add("error",result.getAllErrors());

        //从redis中校对验证码
        if(!codeService.checkPhoneCode(user.getPhone(),userVo.getCode()))return Msg.fail().add("error","验证码错误");

        //检查是否重复注册
        Boolean temp = userService.registerUser(user);
        if(!temp)return Msg.fail().add("error","该用户已注册");

        //转换为密文
        user.setPassword(SecureUtil.md5(user.getPassword()));

        //保存用户
        int save = userService.save(user);
        if(save != 1)return Msg.fail().add("error","用户注册失败，请重试");

        //保存成功
        return Msg.success();
    }

    @GetMapping("/logout")
    public Msg logout(){
        //注销用户
        SecurityUtils.getSubject().logout();
        return Msg.success();
    }
}
