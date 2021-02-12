package com.competition.firemon.utils;

import com.competition.firemon.bean.Gateway;

public class GatewayUtils {

     static Gateway defaultGateway = new Gateway(){{
         setGatewayGhz("580MHz");
         setGatewayTransferRate("1167Mbps");
         setGatewayFlashMemory("8MB");
         setGatewayMemory("64MB");
         setFirmwareVersion("MT7628AN");
         setGatewayMac("1C-68-7E-8D-7D-56");
         setFirstDns("61.134.1.5");
         setNextDns("218.30.19.50");
     }};


    public static Gateway getDefaultGateway(long uid) {
        defaultGateway.setUserId(uid);
        return defaultGateway;
    }
}
