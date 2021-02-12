package com.competition.firemon.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
public class Gateway {
    private Integer gatewayId;

    @NotNull(message = "注册时间不能为空")
    private Date gatewayCreatetime;

    @NotBlank(message = "路由器主频不能为空")
    private String gatewayGhz;

    @NotBlank(message = "无线传输速率不能为空")
    private String gatewayTransferRate;

    @NotBlank(message = "闪存不能为空")
    private String gatewayFlashMemory;

    @NotBlank(message = "内存不能为空")
    private String gatewayMemory;

    @NotBlank(message = "固件版本不能为空")
    private String firmwareVersion;

    @NotBlank(message = "网关MAC不能为空")
    private String gatewayMac;

    @NotBlank(message = "首选DNS不能为空")
    private String firstDns;

    private String nextDns;

    private Long userId;

    public Integer getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getGatewayCreatetime() {
        return gatewayCreatetime;
    }

    public void setGatewayCreatetime(Date gatewayCreatetime) {
        this.gatewayCreatetime = gatewayCreatetime;
    }

    public String getGatewayGhz() {
        return gatewayGhz;
    }

    public void setGatewayGhz(String gatewayGhz) {
        this.gatewayGhz = gatewayGhz == null ? null : gatewayGhz.trim();
    }

    public String getGatewayTransferRate() {
        return gatewayTransferRate;
    }

    public void setGatewayTransferRate(String gatewayTransferRate) {
        this.gatewayTransferRate = gatewayTransferRate == null ? null : gatewayTransferRate.trim();
    }

    public String getGatewayFlashMemory() {
        return gatewayFlashMemory;
    }

    public void setGatewayFlashMemory(String gatewayFlashMemory) {
        this.gatewayFlashMemory = gatewayFlashMemory == null ? null : gatewayFlashMemory.trim();
    }

    public String getGatewayMemory() {
        return gatewayMemory;
    }

    public void setGatewayMemory(String gatewayMemory) {
        this.gatewayMemory = gatewayMemory == null ? null : gatewayMemory.trim();
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion == null ? null : firmwareVersion.trim();
    }

    public String getGatewayMac() {
        return gatewayMac;
    }

    public void setGatewayMac(String gatewayMac) {
        this.gatewayMac = gatewayMac == null ? null : gatewayMac.trim();
    }

    public String getFirstDns() {
        return firstDns;
    }

    public void setFirstDns(String firstDns) {
        this.firstDns = firstDns == null ? null : firstDns.trim();
    }

    public String getNextDns() {
        return nextDns;
    }

    public void setNextDns(String nextDns) {
        this.nextDns = nextDns == null ? null : nextDns.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}