package com.competition.firemon.controller;

import com.competition.firemon.bean.Msg;
import com.competition.firemon.service.SensorService;
import com.competition.firemon.vo.SensorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    SensorService sensorService;

//-------------------------------前端-----------------------------------------------

    //获取传感器动态信息
    @GetMapping("/info")
    public Msg getInfo(){
        SensorVo info = sensorService.getInfo();
        if(info == null)return Msg.fail().add("error","传感器无信息可查询");

        return Msg.success().add("sensor",info);
    }

//-------------------------------嵌入式-----------------------------------------------
    // 上传传感器动态信息
    @PostMapping("/hardware/info")
    public Msg setInfo(@RequestBody SensorVo sensorVo){
        if(!sensorService.setInfo(sensorVo))return Msg.fail().add("error","保存信息失败，请重试");

        return Msg.success();
    }

}
