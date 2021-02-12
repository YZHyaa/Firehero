package com.competition.firemon.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface ChartMapper {

    @Insert("INSERT INTO chart(type,time,data) VALUES (#{type},#{time},#{data})")
    int insertToChart(@Param("type") int type, @Param("time") Date time, @Param("data")int data);

    @Insert("INSERT INTO t_chart(time,data) VALUES (#{time},#{data})")
    int insert( @Param("time") Date time, @Param("data")String json);

    @Select("SELECT data FROM t_chart WHERE time=#{time}")
    String selectOne(@Param("time") Date time);
}
