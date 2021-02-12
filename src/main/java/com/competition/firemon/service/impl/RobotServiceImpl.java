package com.competition.firemon.service.impl;

import com.competition.firemon.bean.Robot;
import com.competition.firemon.bean.RobotExample;
import com.competition.firemon.dao.RobotMapper;
import com.competition.firemon.service.RobotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    RobotMapper robotMapper;

    @Transactional
    @Override
    public boolean binding(String gatewayMac, String robotMac , Integer gatewayId) {
        RobotExample example = new RobotExample();
        RobotExample.Criteria criteria = example.createCriteria();

        criteria.andRobotMacEqualTo(robotMac);
        List<Robot> robots = robotMapper.selectByExample(example);


        if(robots == null || robots.size() != 1){
            return false;
        }

        robots.get(0).setGatewayId(gatewayId);
        try {
            robotMapper.updateByPrimaryKeySelective(robots.get(0));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Robot getRobotByMac(String mac) {
        RobotExample example = new RobotExample();
        RobotExample.Criteria criteria = example.createCriteria();

        criteria.andRobotMacEqualTo(mac);

        //查询
        List<Robot> robots = robotMapper.selectByExample(example);
        if(robots == null || robots.size() != 1)return null;

        return robots.get(0);
    }

    @Transactional
    @Override
    public boolean delete_robot(Integer id) {
        Robot robot = robotMapper.selectByPrimaryKey(id);
        robot.setGatewayId(null);
        try {
            if(robotMapper.updateByPrimaryKey(robot) != 1)return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解绑网关失败");
        }

        return true;
    }

//------------------------------------------嵌入式--------------------------------
    @Transactional
    @Override
    public int save(Robot robot) {
        RobotExample example = new RobotExample();
        RobotExample.Criteria criteria = example.createCriteria();

        return robotMapper.insertSelective(robot);
    }

}
