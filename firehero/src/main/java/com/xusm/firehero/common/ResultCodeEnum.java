package com.xusm.firehero.common;

public enum ResultCodeEnum {
    SUCCESS(true,2000,"成功"),
    REQUEST_ERROR(false,4000,"请求错误"),
    SERVER_ERROR(false,5000,"服务器错误"),
    ;

    // 响应是否成功
    private Boolean success;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}