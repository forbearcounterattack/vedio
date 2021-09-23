package com.fighting.store.user;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
@RefreshScope
@MapperScan("com.fighting.store.user.dao")
public class UserApplication {
    public static void main(final String[] args){
        SpringApplication.run(UserApplication.class,args
        );
    }
}
