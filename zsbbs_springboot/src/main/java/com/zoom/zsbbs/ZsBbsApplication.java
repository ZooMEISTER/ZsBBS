package com.zoom.zsbbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@SpringBootApplication
@MapperScan("com.zoom.zsbbs.mapper")
public class ZsBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZsBbsApplication.class, args);
    }

}
