package com.competition.firemon.vo;

import lombok.Data;

@Data
public class SensorVo {
    private String gatewayMac;

    private String smog;//烟雾

    private String temperature;//温度

    private String co;//一氧化碳

    private String co2;//二氧化碳

    private String pm;
}
