package com.competition.firemon.vo;

import com.competition.firemon.bean.Gateway;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GatewayVo extends Gateway {

    private String status;//工作状态

    private String battery_cap;//电池容量

    private String login_ip;//用户登录ip

    private String gateway_ip;//设备ip

    private String location;//位置信息
}
