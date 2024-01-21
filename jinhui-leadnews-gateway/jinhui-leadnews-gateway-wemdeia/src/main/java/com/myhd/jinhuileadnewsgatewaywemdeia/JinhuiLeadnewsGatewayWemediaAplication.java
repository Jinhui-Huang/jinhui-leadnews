package com.myhd.jinhuileadnewsgatewaywemdeia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class JinhuiLeadnewsGatewayWemediaAplication {

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsGatewayWemediaAplication.class,args);
    }
}
