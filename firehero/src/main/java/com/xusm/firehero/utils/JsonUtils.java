package com.xusm.firehero.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    public static String getString(String key, String value) {
        return JSONObject.parseObject(key).getString(value);
    }
}
