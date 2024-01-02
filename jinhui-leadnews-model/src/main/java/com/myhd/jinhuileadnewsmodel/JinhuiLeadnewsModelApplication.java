package com.myhd.jinhuileadnewsmodel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myhd.jinhuileadnewsmodel.common.user.mapper")
public class JinhuiLeadnewsModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsModelApplication.class, args);
    }

}
