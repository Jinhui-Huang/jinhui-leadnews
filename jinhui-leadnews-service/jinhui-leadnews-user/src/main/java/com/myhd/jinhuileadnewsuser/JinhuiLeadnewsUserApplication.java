package com.myhd.jinhuileadnewsuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 集成nacos的注册中心
@MapperScan("com.myhd.jinhuileadnewsuser.mapper")
public class JinhuiLeadnewsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsUserApplication.class, args);
    }

}
