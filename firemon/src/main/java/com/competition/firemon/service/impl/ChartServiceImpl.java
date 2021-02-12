package com.competition.firemon.service.impl;

import com.competition.firemon.dao.ChartMapper;
import com.competition.firemon.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class ChartServiceImpl implements ChartService {

    @Autowired
    ChartMapper chartMapper;

    @Override
    public boolean saveData(String json) {
        int res = chartMapper.insert(new Date(), json);
        log.info("插入数据成功 " + json);
        return res == 1;
    }

    @Override
    public String getData() {
        Calendar calendar = Calendar.getInstance();
        long currentMillis = calendar.getTimeInMillis();

        String json = "";
        // 可以返回120s内最相近的数据，如果100s内没有数据则返回null
        // 应该设置为传感器发送数据的最大间隔
        // TODO
        for (int i = 0; StringUtils.isBlank(json) && i <= 120; i++) {
            long targetMillis = currentMillis - i*1000;

            int ext;
            if ((ext = (int)targetMillis % 1000) != 0) {
                targetMillis -= ext;
            }
            Date time = new Date(targetMillis);
            json = chartMapper.selectOne(time);
        }

        return json;
    }

}
