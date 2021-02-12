package com.xusm.firehero.dao;

import com.xusm.firehero.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao extends tk.mybatis.mapper.common.Mapper<User> {

    @Select("SELECT * from `user` WHERE uid = #{uid}")
    User selectUserByUid(long uid);

    @Select("SELECT * from `user` where email = #{email}")
    User selectUserByEamil(String email);

    @Select("SELECT * from `user` where phone = #{phone}")
    User selectUserByPhone(String phone);

    @Update("UPDATE `user` set `password` = #{pwd} WHERE uid = #{uid}")
    int updatePwdByUid(String pwd, long uid);
}
