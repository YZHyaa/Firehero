package com.competition.firemon.service.impl;

import com.competition.firemon.bean.Gateway;
import com.competition.firemon.bean.User;
import com.competition.firemon.service.GatewayService;
import com.competition.firemon.service.SensorService;
import com.competition.firemon.utils.RedisUtil;
import com.competition.firemon.vo.SensorVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SensorServiceImpl implements SensorService {

    private static final String sensor = ":sensor";

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    GatewayService gatewayService;

    @Override
    public SensorVo getInfo() {
        User profile = (User)SecurityUtils.getSubject().getSession().getAttribute("profile");

        Gateway gatewayByUid = gatewayService.getGatewayByUid(profile.getUid());
        if(gatewayByUid == null)return null;
        if(!redisUtil.hasKey(gatewayByUid.getGatewayMac()+sensor)){
            return null;
        }
        Map<Object, Object> hmget = redisUtil.hmget(gatewayByUid.getGatewayMac() + sensor);

        SensorVo sensorVo = new SensorVo();
        sensorVo.setGatewayMac(gatewayByUid.getGatewayMac());
        sensorVo.setSmog((String) hmget.get("smog"));
        sensorVo.setTemperature((String) hmget.get("temperature"));
        sensorVo.setCo((String) hmget.get("co"));
        sensorVo.setCo2((String) hmget.get("co2"));
        sensorVo.setPm((String) hmget.get("pm"));

        return sensorVo;
    }

    @Override
    public boolean setInfo(SensorVo sensorVo) {

        //准备存储信息
        HashMap<String,Object> map = new HashMap<>();
        map.put("smog",sensorVo.getSmog());
        map.put("temperature",sensorVo.getTemperature());
        map.put("co",sensorVo.getCo());
        map.put("co2",sensorVo.getCo2());
        map.put("pm",sensorVo.getCo2());

        String gatewayMac = sensorVo.getGatewayMac();

        if(redisUtil.hasKey(gatewayMac+sensor)){
            redisUtil.hmset(gatewayMac+sensor,map);
        }else {
            //只写入部分变更数据，减少写入时间
            for (String item : map.keySet()) {
                Object value = map.get(item);
                if(value != null && !value.equals(redisUtil.hget(sensorVo.getGatewayMac()+sensor,item))){
                    //写入变更数据
                    if(!redisUtil.hset(sensorVo.getGatewayMac()+sensor,item, value))return false;
                }
            }
        }

        return true;
    }

}
