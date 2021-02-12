package com.competition.firemon.controller;

import com.competition.firemon.bean.Msg;
import com.competition.firemon.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class TestController {
    @PostMapping("/link")
    public Msg Connection(@RequestBody String json){

        log.info(json);

        HashMap<String,Object> map = new HashMap<>();

        map.put("a",JsonUtils.getString(json,"a"));
        map.put("b",JsonUtils.getString(json,"b"));
        map.put("c",JsonUtils.getString(json,"c"));
        map.put("d",JsonUtils.getString(json,"d"));
        map.put("e",JsonUtils.getString(json,"e"));

        return Msg.success().add("data",json);
    }

    @GetMapping
    public String hello() {
        return "start...";
    }
}
