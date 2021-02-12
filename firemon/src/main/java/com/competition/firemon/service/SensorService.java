package com.competition.firemon.service;

import com.competition.firemon.vo.SensorVo;

public interface SensorService {

     SensorVo getInfo();

     boolean setInfo(SensorVo sensorVo);
}
