package com.competition.firemon.controller;

import com.competition.firemon.bean.Gateway;
import com.competition.firemon.bean.Msg;
import com.competition.firemon.bean.Robot;
import com.competition.firemon.bean.User;
import com.competition.firemon.service.GatewayService;
import com.competition.firemon.service.RobotService;
import com.competition.firemon.utils.JsonUtils;
import com.competition.firemon.utils.ValidationUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    GatewayService gatewayService;

    @Autowired
    RobotService robotService;

//-------------------------------前端-----------------------------------------------
    //与网关绑定
    @PostMapping("/binding")
    public Msg binding(@RequestBody String json){
        String gatewayMac = JsonUtils.getString(json, "gatewayMac");
        String robotMac = JsonUtils.getString(json, "robotMac");

        User profile = (User)SecurityUtils.getSubject().getSession().getAttribute("profile");
        Gateway gatewayByUid = gatewayService.getGatewayByUid(profile.getUid());

        //核对mac信息是否是用户绑定的网关
        if(gatewayByUid == null)return Msg.fail().add("error","请先绑定网关");
        if(gatewayMac == null || !gatewayMac.equals(gatewayByUid.getGatewayMac())){
            return Msg.fail().add("error","用户网关信息不一致");
        }

        //绑定
        if(!robotService.binding(gatewayMac,robotMac,gatewayByUid.getGatewayId()))return Msg.fail().add("error","机器人绑定网关失败");

        return Msg.success();
    }

    //解绑机器人
    @DeleteMapping("/{id}")
    public Msg delete_robot(@PathVariable("id") Integer id){
        if (!robotService.delete_robot(id))return Msg.fail().add("error","解绑机器人失败");

        return Msg.success();
    }

//-------------------------------嵌入式-----------------------------------------------
    @PostMapping("/register")
    public Msg register(@RequestBody Robot robot){
        ValidationUtil.ValidResult result = ValidationUtil.validateBean(robot);

        if(result.hasErrors())return Msg.fail().add("error",result.getAllErrors());

        //查重
        Robot robotByMac = robotService.getRobotByMac(robot.getRobotMac());
        if(robotByMac != null)return Msg.fail().add("error","该机器人已被注册，请勿重复操作");

        if(robotService.save(robot) != 1)return Msg.fail().add("error","机器人注册失败");
        return Msg.success();
    }
}
