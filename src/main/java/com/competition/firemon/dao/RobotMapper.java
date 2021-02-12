package com.competition.firemon.dao;

import com.competition.firemon.bean.Robot;
import com.competition.firemon.bean.RobotExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RobotMapper {
    int countByExample(RobotExample example);

    int deleteByExample(RobotExample example);

    int deleteByPrimaryKey(Integer robotId);

    int insert(Robot record);

    int insertSelective(Robot record);

    List<Robot> selectByExample(RobotExample example);

    Robot selectByPrimaryKey(Integer robotId);

    int updateByExampleSelective(@Param("record") Robot record, @Param("example") RobotExample example);

    int updateByExample(@Param("record") Robot record, @Param("example") RobotExample example);

    int updateByPrimaryKeySelective(Robot record);

    int updateByPrimaryKey(Robot record);
}