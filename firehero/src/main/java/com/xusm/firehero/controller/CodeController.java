package com.xusm.firehero.controller;

import com.xusm.firehero.common.R;
import com.xusm.firehero.service.CodeService;
import com.xusm.firehero.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @PostMapping("/phone")
    public R sendPhoneCode(@RequestBody String json) {
        String phone = JsonUtils.getString(json,"phone");
        if (!this.codeService.sendPhoneCode(phone)) {
            return R.serverError().message("手机验证码发送失败");
        }
        return R.ok();
    }

    @PostMapping("/email")
    public R sendEmailCode(@RequestBody String json) {
        String email = JsonUtils.getString(json, "email");
        if (!this.codeService.sendEmailCode(email)) {
            return R.serverError().message("邮箱验证码发送失败");
        }
        return R.ok();
    }
}
