package com.competition.firemon.controller;

import com.competition.firemon.bean.Gateway;
import com.competition.firemon.bean.Msg;
import com.competition.firemon.bean.User;
import com.competition.firemon.service.GatewayService;
import com.competition.firemon.utils.JsonUtils;
import com.competition.firemon.utils.ValidationUtil;
import com.competition.firemon.vo.GatewayVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/gateway")
@Slf4j
public class GatewayController {

    @Autowired
    GatewayService gatewayService;


//-------------------------------前端-----------------------------------------------

    //绑定路由器
    @PostMapping("/binding")
    public Msg binding(@RequestBody String json){
        String mac = JsonUtils.getString(json, "mac");

        User profile = (User) SecurityUtils.getSubject().getSession().getAttribute("profile");
        if(!gatewayService.binding(mac,profile.getUid()))return Msg.fail().add("error","绑定网关失败");

        return Msg.success();
    }

    //修改路由器信息
    @PutMapping("/info")
    public Msg update_gateway(@RequestBody Gateway gateway){
        if(!gatewayService.update_gateway(gateway))return Msg.fail().add("error","路由器信息修改失败");

        return Msg.success();
    }

    //获取路由器信息
    @GetMapping("/info")
    public Msg gateway_page(HttpServletRequest request){
        GatewayVo gatewayVo = new GatewayVo();
        //获取用户登录ip
        gatewayVo.setLogin_ip(request.getRemoteHost());

        //获取当前用户
        User profile = (User) SecurityUtils.getSubject().getSession().getAttribute("profile");
        if(profile == null){
            return Msg.fail().add("error","请重新登录");
        }else {
            //获取当前用户id
            gatewayVo.setUserId(profile.getUid());
        }

        GatewayVo res = gatewayService.gateway_page(gatewayVo);
        if(res == null)return Msg.fail().add("error","找不到对应路由器信息");

        return Msg.success().add("gatewayInfo",res);
    }

    //解绑路由器
    @DeleteMapping("/info/{id}")
    public Msg delete_gateway(@PathVariable("id") Integer id){
        if (!gatewayService.delete_gateway(id))return Msg.fail().add("error","解绑路由器失败");

        return Msg.success();
    }

//-------------------------------嵌入式----------------------------------------------

    //注册路由器
    @PostMapping("/hardware/register")
    public Msg register(@RequestBody String json){
        Gateway gateway = new Gateway();
        gateway.setGatewayCreatetime(new Date());
        gateway.setGatewayGhz(JsonUtils.getString(json,"gatewayGhz"));
        gateway.setGatewayTransferRate(JsonUtils.getString(json,"gatewayTransferRate"));
        gateway.setGatewayFlashMemory(JsonUtils.getString(json,"gatewayFlashMemory"));
        gateway.setGatewayMemory(JsonUtils.getString(json,"gatewayMemory"));
        gateway.setFirmwareVersion(JsonUtils.getString(json,"firmwareVersion"));
        gateway.setGatewayMac(JsonUtils.getString(json,"gatewayMac"));
        gateway.setFirstDns(JsonUtils.getString(json,"firstDns"));

//        //验证用户
//        User profile = (User) SecurityUtils.getSubject().getSession().getAttribute("profile");
//        if(profile == null){
//            return Msg.fail().add("error","请重新登录");
//        }else if(profile.getUid().intValue() != gateway.getUserId()){
//            return Msg.fail().add("error","用户绑定失败");
//        }

        //查重
        Gateway gatewayByMac = gatewayService.getGatewayByMac(gateway.getGatewayMac());
        if(gatewayByMac != null)return Msg.fail().add("error","该路由器已注册");

        //注册
        if(gatewayService.save(gateway) != 1)return Msg.fail().add("error","网关注册失败");

        return Msg.success();
    }

    //将动态信息存入redis中，等待前端来取
    @PostMapping("/hardware/info")
    public Msg gateway_dynamic_info(@RequestBody String json,HttpServletRequest request){
        GatewayVo gatewayVo = new GatewayVo();
        gatewayVo.setGatewayMac(JsonUtils.getString(json,"gatewayMac"));
        gatewayVo.setStatus(JsonUtils.getString(json,"gatewayMac"));
        gatewayVo.setBattery_cap(JsonUtils.getString(json,"battery_cap"));
        gatewayVo.setLocation(JsonUtils.getString(json,"location"));

        gatewayVo.setGateway_ip(request.getRemoteHost());
        if(!gatewayService.gateway_dynamic_info(gatewayVo))return Msg.fail().add("error","保存信息失败，请重试");

        return Msg.success();
    }
}
