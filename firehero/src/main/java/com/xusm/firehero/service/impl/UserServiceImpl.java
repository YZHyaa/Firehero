package com.xusm.firehero.service.impl;

import com.xusm.firehero.common.role.ErrorCodeRole;
import com.xusm.firehero.config.prop.JwtProperties;
import com.xusm.firehero.dao.UserDao;
import com.xusm.firehero.entity.User;
import com.xusm.firehero.service.CodeService;
import com.xusm.firehero.service.UserService;
import com.xusm.firehero.utils.CodecUtils;
import com.xusm.firehero.utils.JsonUtils;
import com.xusm.firehero.utils.JwtUtils;
import com.xusm.firehero.vo.PwdVo;
import com.xusm.firehero.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Email;

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CodeService codeService;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public Long queryAccount(String account, String password) {
        User u = null;
        if ((u = this.userDao.selectUserByEamil(account)) == null) {
            if ((u = this.userDao.selectUserByPhone(account)) == null) {
                return (long)-1;
            }
        }
        if (! StringUtils.equals(CodecUtils.md5Hex(password, u.getSalt()), u.getPassword())) {
            return (long)-1;
        }
        return u.getUid();
    }

    public String generateToken(Long uid) {
        String token = "";
        User user = this.userDao.selectUserByUid(uid);
        try {
            token =  JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire() * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public int register(UserVo userVo) {
        if (!this.codeService.checkPhoneCode(userVo.getPhone(), userVo.getCode())) {
            return ErrorCodeRole.PHONE_ERROR;
        }
        User user = new User();
        user.setPhone(userVo.getPhone());
        if (this.userDao.selectCount(user) != 0 ){
            return ErrorCodeRole.PARAM_ERROR;
        }
        user.setEmail(userVo.getEmail());
        if (this.userDao.selectCount(user) != 0) {
            return ErrorCodeRole.EMAIL_ERROR;
        }

        user.setUsername(userVo.getUsername());
        // md5加密
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        user.setPassword(CodecUtils.md5Hex(userVo.getPassword(), salt));

        return this.userDao.insert(user);
    }

    @Override
    public int findPwd(String json) {
        String email = JsonUtils.getString(json, "email");
        String code = JsonUtils.getString(json, "code");
        String password = JsonUtils.getString(json, "password");

        if (!this.codeService.checkEmailCode(email, code)) {
            return ErrorCodeRole.EMAIL_ERROR;
        }
        User user = this.userDao.selectUserByEamil(email);
        if (user == null) {
            return ErrorCodeRole.PARAM_ERROR;
        }

        return userDao.updatePwdByUid(CodecUtils.md5Hex(password, user.getSalt()), user.getUid());
    }

    @Override
    public int changePwd(PwdVo pwdVo) {
        if (!this.codeService.checkPhoneCode(pwdVo.getPhone(), pwdVo.getCode())) {
            return ErrorCodeRole.PHONE_ERROR;
        }
        User user = this.userDao.selectUserByPhone(pwdVo.getPhone());
        if (user == null || !StringUtils.equals(user.getPassword(), CodecUtils.md5Hex(pwdVo.getOldPwd(), user.getSalt()))) {
            return ErrorCodeRole.PARAM_ERROR;
        }

        return userDao.updatePwdByUid(CodecUtils.md5Hex(pwdVo.getNewPwd(), user.getSalt()), user.getUid());
    }
}
