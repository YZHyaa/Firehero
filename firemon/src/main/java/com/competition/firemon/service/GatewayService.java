package com.competition.firemon.service;

import com.competition.firemon.bean.Gateway;
import com.competition.firemon.vo.GatewayVo;

public interface GatewayService {

    boolean binding(String mac,Long uid);

    Gateway getGatewayByMac(String mac);

    Gateway getGatewayByUid(Long uid);

    boolean update_gateway(Gateway gateway);

    boolean delete_gateway(Integer id);

    GatewayVo gateway_page(GatewayVo gatewayVo);

    int save (Gateway gateway);

    boolean gateway_dynamic_info(GatewayVo gatewayVo);
}
