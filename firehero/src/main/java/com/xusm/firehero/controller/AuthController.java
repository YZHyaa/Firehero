package com.xusm.firehero.controller;
import com.xusm.firehero.common.R;
import com.xusm.firehero.config.prop.JwtProperties;
import com.xusm.firehero.entity.User;
import com.xusm.firehero.utils.JwtUtils;
import com.xusm.firehero.utils.MyCookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Resource
    private JwtProperties jwtProperties;

    @Value("${firehero.domain}")
    private String domain;

    @GetMapping("/user/verify")
    public R verify(
            HttpServletResponse response, HttpServletRequest request,
            @CookieValue(value = "FIREHERO_TOKEN",required = false) String token
        ){
        if (!StringUtils.isBlank(token)) {
            User user = null;
            try {
                user = JwtUtils.getInfoFromToken(token,this.jwtProperties.getPublicKey());
                if(user != null){
                    token = JwtUtils.generateToken(user,jwtProperties.getPrivateKey(),jwtProperties.getExpire()*60);
                    MyCookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getCookieMaxAge()*60,domain);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.error().code(4001).message("未登录");
    }
}
