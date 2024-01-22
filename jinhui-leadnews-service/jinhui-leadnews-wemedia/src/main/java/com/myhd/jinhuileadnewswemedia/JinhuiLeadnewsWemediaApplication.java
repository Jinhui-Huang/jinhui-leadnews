package com.myhd.jinhuileadnewswemedia;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.myhd.jinhuileadnewswemedia.mapper")
public class JinhuiLeadnewsWemediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsWemediaApplication.class,args);
    }

    /**
     * Description: mybatisPlusInterceptor mybatis-plus的分页拦截器
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
