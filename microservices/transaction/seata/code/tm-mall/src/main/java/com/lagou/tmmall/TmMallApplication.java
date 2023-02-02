package com.lagou.tmmall;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableAutoDataSourceProxy
@EnableFeignClients
public class TmMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(TmMallApplication.class, args);
    }

}
