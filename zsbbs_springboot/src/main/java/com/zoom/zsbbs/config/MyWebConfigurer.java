package com.zoom.zsbbs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Value("${file.dataFolder}")
    String fileDataFolderPath;
    @Value("${data.postImageFolder}")
    String dataPostImageFolderPath;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 所有请求都允许跨域，使用这种配置就不需要
         * 在interceptor中配置header了
         */
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    //映射虚拟路径 来实现对本地文件的访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(dataPostImageFolderPath).addResourceLocations(fileDataFolderPath + dataPostImageFolderPath);
    }


}