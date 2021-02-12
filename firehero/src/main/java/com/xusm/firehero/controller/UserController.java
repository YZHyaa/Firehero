package com.xusm.firehero.controller;

import com.xusm.firehero.common.R;
import com.xusm.firehero.common.role.ErrorCodeRole;
import com.xusm.firehero.config.prop.JwtProperties;
import com.xusm.firehero.service.UserService;
import com.xusm.firehero.utils.JsonUtils;
import com.xusm.firehero.utils.MyCookieUtils;
import com.xusm.firehero.vo.PwdVo;
import com.xusm.firehero.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private JwtProperties jwtProperties;

    @Value("${firehero.domain}")
    private String domain;

    @PostMapping("login")
    public R login(@RequestBody String json,HttpServletRequest request, HttpServletResponse response) {
        String account = JsonUtils.getString(json, "account");
        String password = JsonUtils.getString(json, "password");

        Long uid;
        if ((uid = this.userService.queryAccount(account, password)) == -1) {
            return R.error().message("登录失败");
        }
        String token = this.userService.generateToken(uid);

        MyCookieUtils.setCookie(request, response, jwtProperties.getCookieName(), token,
                jwtProperties.getCookieMaxAge()*60, domain);
        return R.ok();
    }

    @PostMapping("register")
    public R register(@RequestBody UserVo userVo) {
        int code = this.userService.register(userVo);

        if (code == ErrorCodeRole.PHONE_ERROR) {
            return R.error().code(4001).message("验证码错误");
        } else if (code == ErrorCodeRole.PARAM_ERROR) {
            return R.error().code(4002).message("手机号已存在");
        } else if (code == ErrorCodeRole.EMAIL_ERROR) {
            return R.error().code(4003).message("邮箱已存在");
        }

        return R.ok();
    }

    @PostMapping("pwd")
    public R findPwd(@RequestBody String json) {
        int state = userService.findPwd(json);
        if (state == ErrorCodeRole.EMAIL_ERROR) {
            return R.error().code(4001).message("验证码错误");
        } else if (state == ErrorCodeRole.PARAM_ERROR) {
            return R.error().code(4002).message("无该用户信息");
        }
        return R.ok();
    }

    @PutMapping("pwd")
    public R changePwd(@RequestBody PwdVo pwdVo) {
        int state = this.userService.changePwd(pwdVo);

        if (state == ErrorCodeRole.PHONE_ERROR) {
            return R.error().code(4001).message("验证码错误");
        } else if (state == ErrorCodeRole.PARAM_ERROR) {
            return R.error().code(4002).message("无该用户信息");
        }

        return R.ok();
    }

}
