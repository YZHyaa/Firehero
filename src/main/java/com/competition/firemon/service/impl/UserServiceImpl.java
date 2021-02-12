package com.competition.firemon.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.druid.util.StringUtils;
import com.competition.firemon.bean.Gateway;
import com.competition.firemon.bean.User;
import com.competition.firemon.bean.UserExample;
import com.competition.firemon.dao.UserMapper;
import com.competition.firemon.service.GatewayService;
import com.competition.firemon.service.UserService;
import com.competition.firemon.utils.GatewayUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GatewayService gatewayService;

    @Transactional
    @Override
    public int save(User user) {
        try {
            int resCode = userMapper.insertSelective(user);
            gatewayService.save(GatewayUtils.getDefaultGateway(userMapper.selectUidByPhone(user.getPhone())));
            return resCode;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("插入用户失败，可能原因是有字段为null");
        }
        return 0;
    }

    //true代表没注册，false代表之前注册了
    @Override
    public Boolean registerUser(User user) {
        //没有id则根据条件查
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(user.getPhone())){
            //对唯一的条件，先筛选
            criteria.andPhoneEqualTo(user.getPhone());
            if(userMapper.countByExample(example) != 0)return false;
        }

        //虽然这个办法有点笨，但是可以消除上一个条件的影响
        example = new UserExample();
        criteria = example.createCriteria();

        if(!StringUtils.isEmpty(user.getEmail())){
            //对唯一的条件，先筛选
            criteria.andEmailEqualTo(user.getEmail());
            if(userMapper.countByExample(example) != 0)return false;
        }


        return true;
    }

    @Override
    public User login(String account,String password) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if(account.contains("@"))criteria.andEmailEqualTo(account);
        else criteria.andPhoneEqualTo(account);

        List<User> users = userMapper.selectByExample(example);
        //结果集不唯一
        if(users.size() != 1)return null;

        //校验密码
        if(!StringUtils.equals(users.get(0).getPassword(), SecureUtil.md5(password)))return null;

        //登陆成功
        return users.get(0);
    }

    @Transactional
    @Override
    public Boolean findPwd(String email, String code, String password) {
        //条件筛选
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andEmailEqualTo(email);

        List<User> users = userMapper.selectByExample(example);
        //结果集不唯一
        if(users.size() != 1)return false;

        User user = users.get(0);
        user.setPassword(SecureUtil.md5(password));

        try {
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户找回密码操作失败");
        }

        return true;
    }

    @Transactional
    @Override
    public Boolean updatePwd(String phone,String oldPwd, String newPwd) {
        //查询用户
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        //之前做过非空判断，这里不判断了
        criteria.andPhoneEqualTo(phone);

        List<User> users = userMapper.selectByExample(example);
        //结果集不唯一
        if(users.size() != 1)return false;

        User user = users.get(0);
        //校验旧密码
        if(!StringUtils.equals(user.getPassword(),SecureUtil.md5(oldPwd)))return false;

        //执行修改方法
        user.setPassword(SecureUtil.md5(newPwd));

        try {
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户修改密码操作失败");
        }

        return true;
    }

}
