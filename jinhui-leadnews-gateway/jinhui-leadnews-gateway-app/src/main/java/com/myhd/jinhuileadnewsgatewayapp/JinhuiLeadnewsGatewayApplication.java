package com.myhd.jinhuileadnewsgatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description: JinhuiLeadnewsGatewayApplication
 * <br></br>
 * className: JinhuiLeadnewsGatewayApplication
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsgateway
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/2 23:41
 */

@SpringBootApplication
@EnableDiscoveryClient
public class JinhuiLeadnewsGatewayApplication {
    public JinhuiLeadnewsGatewayApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsGatewayApplication.class, args);
    }
}
