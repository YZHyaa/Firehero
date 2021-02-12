package com.competition.firemon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @Description 跨域设置
 * @Author iterge
 * @Date 2019/6/6 18:50
 * addMapping("/**") 对接口配置跨域设置 /**代表所有接口
 * allowedHeaders("*") 允许所有的请求头
 * allowedMethods("*") 允许所有的方法 也可以设置为("POST", "GET", "PUT", "OPTIONS", "DELETE")
 * allowedOrigins("*") 允许所有的域（源地址）
 */
@Configuration
@Slf4j
public class WebMvcConfig{

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        log.info("**********WebMvcConfig**********");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
    /*
     * 跨域过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
