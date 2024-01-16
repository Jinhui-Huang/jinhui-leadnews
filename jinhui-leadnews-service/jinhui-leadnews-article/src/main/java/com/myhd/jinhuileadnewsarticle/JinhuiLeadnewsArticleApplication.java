package com.myhd.jinhuileadnewsarticle;

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
@MapperScan("com.myhd.jinhuileadnewsarticle.mapper")
public class JinhuiLeadnewsArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinhuiLeadnewsArticleApplication.class,args);
    }

    /**
     * Description: mybatisPlusInterceptor mybatis plus 分页插件
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @author jinhui-huang
     * @Date 2024/1/16
     * */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
