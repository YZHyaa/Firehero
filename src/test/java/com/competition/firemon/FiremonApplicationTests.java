package com.competition.firemon;

import com.competition.firemon.bean.User;
import com.competition.firemon.service.ChartService;
import com.competition.firemon.service.UserService;
import com.competition.firemon.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class FiremonApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    @Autowired
    ChartService chartService;

    @Test
    void contextLoads() {
        //准备存储信息
        HashMap<String,Object> map = new HashMap<>();
        map.put("status","正常工作");
        map.put("battery_cap","75%");
        map.put("login_ip","192.168.0.");
        map.put("gateway_ip","192.168.0.1");
        map.put("location","西北仓库");

        for (String item : map.keySet()) {
            Object value = map.get(item);
            redisUtil.hset("1C-68-7E-8D-7D-56",item, value);
        }
    }

    @Test
    void testRegis() {
        User user = new User();
        user.setPassword("123456");
        user.setUsername("test");
        user.setEmail("1234566822@qq.com");
        user.setPhone("18189093292");
        int uid = userService.save(user);
        System.out.println(uid);
    }

    @Test
    void testChart() {
//        String json = "{\"smog\":200, \"co\":200, \"co2\":200, \"pm\":200, \"temperature\":200}";
//        boolean success = chartService.saveData(json);
//        System.out.println(success);
        String json = chartService.getData();
        System.out.println(json);
    }

    @Test
    void testDir() {
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
    }

}
