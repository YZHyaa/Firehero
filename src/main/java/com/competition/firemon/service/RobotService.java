package com.competition.firemon.service;

import com.competition.firemon.bean.Robot;

public interface RobotService {

    boolean binding(String gatewayMac,String robotMac,Integer gatewayId);

    Robot getRobotByMac(String mac);

    boolean delete_robot(Integer id);

    int save (Robot robot);

}
