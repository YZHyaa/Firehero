package com.xusm.firehero.service;

import com.xusm.firehero.vo.PwdVo;
import com.xusm.firehero.vo.UserVo;

public interface UserService {
    Long queryAccount(String account, String password);

    int register(UserVo userVo);

    int findPwd(String json);

    int changePwd(PwdVo pwdVo);

    String generateToken(Long uid);
}
