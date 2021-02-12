package com.competition.firemon.bean;


import java.util.HashMap;
import java.util.Map;

//通用的返回的类
public class Msg {

    private int status;
    private String msg;

    public static final Integer SUCCESS = 2000;
    public static final Integer FAIL = 4000;
    //用户要返回给浏览器的数据
    Map<String,Object> extend = new HashMap<>();

    public static Msg success(){
        Msg result = new Msg();
        result.setStatus(SUCCESS);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setStatus(FAIL);
        result.setMsg("处理失败");
        return result;
    }

    public Msg add(String key, Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
