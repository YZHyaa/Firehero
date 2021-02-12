package com.competition.firemon.config.video;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileVideoConfig {
    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofKilobytes(1048576)); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofKilobytes(1048576));
        return factory.createMultipartConfig();
    }

}
