package com.competition.firemon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.util.StringUtils;
import com.competition.firemon.bean.Gateway;
import com.competition.firemon.bean.GatewayExample;
import com.competition.firemon.dao.GatewayMapper;
import com.competition.firemon.service.GatewayService;
import com.competition.firemon.utils.RedisUtil;
import com.competition.firemon.vo.GatewayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GatewayServiceImpl implements GatewayService {

    @Resource
    GatewayMapper gatewayMapper;

    @Autowired
    RedisUtil redisUtil;

    @Transactional
    @Override
    public boolean binding(String mac, Long uid) {
        //查看是否已经被绑定
        Gateway gatewayByMac = getGatewayByMac(mac);
        if(gatewayByMac == null || gatewayByMac.getUserId() != null)return false;

        gatewayByMac.setUserId(uid);
        try {
            gatewayMapper.updateByPrimaryKeySelective(gatewayByMac);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("绑定网关失败");
            return false;
        }
    }

    @Override
    public Gateway getGatewayByMac(String mac) {
        GatewayExample example = new GatewayExample();
        GatewayExample.Criteria criteria = example.createCriteria();

        //条件筛选
        criteria.andGatewayMacEqualTo(mac);

        List<Gateway> gateways = gatewayMapper.selectByExample(example);
        if(gateways == null || gateways.size() == 0)return null;

        //始终返回第一个网关，因为项目默认是一个用户一个网关
        return gateways.get(0);
    }

    @Override
    public Gateway getGatewayByUid(Long uid) {
        GatewayExample example = new GatewayExample();
        GatewayExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(uid);

        List<Gateway> gateways = gatewayMapper.selectByExample(example);

        if(gateways == null || gateways.size() == 0)return null;

        //始终返回第一个网关，因为项目默认是一个用户一个网关
        return gateways.get(0);
    }

    @Transactional
    @Override
    public boolean update_gateway(Gateway gateway) {
        try {
            if (gatewayMapper.updateByPrimaryKeySelective(gateway) != 1) return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("修改网关信息失败");
        }

        return true;
    }

    @Transactional
    @Override
    public boolean delete_gateway(Integer id) {
        Gateway gateway = gatewayMapper.selectByPrimaryKey(id);
        gateway.setUserId(null);
        try {
            if(gatewayMapper.updateByPrimaryKey(gateway) != 1)return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解绑网关失败");
        }

        return true;
    }

    @Override
    public GatewayVo gateway_page(GatewayVo gatewayVo) {
        String login_ip = "login_ip";

        //默认一个管理者只拥有一个网关
        Gateway gatewayByUid = getGatewayByUid(gatewayVo.getUserId());
        if(gatewayByUid == null)return null;

        //查看key
        if(!redisUtil.hasKey(gatewayByUid.getGatewayMac()))return null;

        //变更登录ip
        if(!StringUtils.isEmpty(gatewayVo.getLogin_ip())
                && !gatewayVo.getLogin_ip().equals(redisUtil.hget(gatewayByUid.getGatewayMac(),login_ip))){
            //写入变更数据
            if(!redisUtil.hset(gatewayByUid.getGatewayMac(),login_ip, gatewayVo.getLogin_ip()))return null;
        }

        //获取新的对象
        Map<Object, Object> map = redisUtil.hmget(gatewayByUid.getGatewayMac());
        gatewayVo.setStatus((String) map.get("status"));
        gatewayVo.setBattery_cap((String) map.get("battery_cap"));
        gatewayVo.setLogin_ip((String) map.get("login_ip"));
        gatewayVo.setGateway_ip((String) map.get("gateway_ip"));
        gatewayVo.setLocation((String) map.get("location"));

        BeanUtil.copyProperties(gatewayByUid,gatewayVo);

        return gatewayVo;
    }

//-------------------------------嵌入式----------------------------------------------

    @Transactional
    @Override
    public int save(Gateway gateway) {
        try {
            return gatewayMapper.insertSelective(gateway);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("注册网关失败");
        }

        return 0;
    }


    @Override
    public boolean gateway_dynamic_info(GatewayVo gatewayVo) {
        if(StringUtils.isEmpty(gatewayVo.getGatewayMac()))return false;

        //准备存储信息
        HashMap<String,Object> map = new HashMap<>();
        map.put("status",gatewayVo.getStatus());
        map.put("battery_cap",gatewayVo.getBattery_cap());
        map.put("login_ip",gatewayVo.getLogin_ip());
        map.put("gateway_ip",gatewayVo.getGateway_ip());
        map.put("location",gatewayVo.getLocation());

        //判断redis是否存在key
        if(!redisUtil.hasKey(gatewayVo.getGatewayMac())){
            return redisUtil.hmset(gatewayVo.getGatewayMac(),map);
        }else{
            //只写入部分变更数据，减少写入时间
            for (String item : map.keySet()) {
                Object value = map.get(item);
                if(value != null && !value.equals(redisUtil.hget(gatewayVo.getGatewayMac(),item))){
                    //写入变更数据
                    if(!redisUtil.hset(gatewayVo.getGatewayMac(),item, value))return false;
                }
            }
        }

        return true;
    }


}
