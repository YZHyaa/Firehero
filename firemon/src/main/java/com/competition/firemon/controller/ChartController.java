package com.competition.firemon.controller;

import com.competition.firemon.bean.Msg;
import com.competition.firemon.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    ChartService chartService;

    @PostMapping
    public Msg saveData(@RequestBody String json) {
        if (chartService.saveData(json)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @GetMapping
    public String getData() {
        String res = chartService.getData();
        return res;
    }
}
