package com.competition.firemon.bean;

import javax.validation.constraints.NotBlank;

public class Robot {
    private Integer robotId;

    private Integer gatewayId;

    @NotBlank(message = "设备型号不能为空")
    private String unitType;

    @NotBlank(message = "机器人MAC地址不能为空")
    private String robotMac;

    public Integer getRobotId() {
        return robotId;
    }

    public void setRobotId(Integer robotId) {
        this.robotId = robotId;
    }

    public Integer getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType == null ? null : unitType.trim();
    }

    public String getRobotMac() {
        return robotMac;
    }

    public void setRobotMac(String robotMac) {
        this.robotMac = robotMac == null ? null : robotMac.trim();
    }
}