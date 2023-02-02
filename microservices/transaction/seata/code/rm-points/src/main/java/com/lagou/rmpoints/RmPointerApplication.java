package com.lagou.rmpoints;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lagou.rmpoints.repository")
public class RmPointerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmPointerApplication.class, args);
    }

}
