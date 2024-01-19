package com.myhd.jinhuileadnewswemedia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.heima.wemedia.mapper")
public class JinhuiLeadnewsWemediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsWemediaApplication.class,args);
    }
}
