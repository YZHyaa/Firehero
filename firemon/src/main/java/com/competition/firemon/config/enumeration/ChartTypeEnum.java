package com.competition.firemon.config.enumeration;

public enum ChartTypeEnum {

    SMOG(1), CO(2), CO2(3), PM(4), TEMPERATURE(5);

    int type;
    ChartTypeEnum(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }
}
