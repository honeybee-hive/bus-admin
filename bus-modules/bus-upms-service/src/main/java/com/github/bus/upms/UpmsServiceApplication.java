package com.github.bus.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringCloudApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.github.bus.upms.dao")
public class UpmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsServiceApplication.class, args);
    }

}
