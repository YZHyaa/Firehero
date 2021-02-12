package com.xusm.firehero.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class MyCookieUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCookieUtils.class);

    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,Integer cookieMaxAge,String domain){
        setCookie(request,response,cookieName,cookieValue,cookieMaxAge,domain,null,null);
    }

    private static final void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, Integer cookieMaxAge,String domain, String encodeString, Boolean httpOnly) {
        try {
            if(StringUtils.isBlank(encodeString)) {
                encodeString = "utf-8";
            }

            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxAge != null && cookieMaxAge > 0)
                cookie.setMaxAge(cookieMaxAge);
            if (null != request)// 设置域名的cookie
                cookie.setDomain(domain);
            cookie.setPath("/");

            if(httpOnly != null) {
                cookie.setHttpOnly(httpOnly);
            }
            response.addCookie(cookie);
        } catch (Exception e) {
            LOGGER.error("Cookie Encode Error.", e);
        }
    }
}
