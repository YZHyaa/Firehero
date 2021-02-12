package com.competition.firemon.service;

import com.competition.firemon.bean.User;


public interface UserService {

    int save(User user);

    Boolean registerUser(User user);

    User login(String account,String password);

    Boolean findPwd(String email,String code,String password);

    Boolean updatePwd(String phone,String oldPwd,String newPwd);
}
