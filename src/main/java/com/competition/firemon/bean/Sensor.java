package com.competition.firemon.bean;

public class Sensor {
    private Integer sensorId;

    private Integer gatewayId;

    private String unitType;

    private String sensorMac;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
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

    public String getSensorMac() {
        return sensorMac;
    }

    public void setSensorMac(String sensorMac) {
        this.sensorMac = sensorMac == null ? null : sensorMac.trim();
    }
}