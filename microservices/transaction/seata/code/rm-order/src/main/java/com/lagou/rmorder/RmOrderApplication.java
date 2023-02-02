package com.lagou.rmorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.lagou.rmorder.repository")
public class RmOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmOrderApplication.class, args);
    }

}
