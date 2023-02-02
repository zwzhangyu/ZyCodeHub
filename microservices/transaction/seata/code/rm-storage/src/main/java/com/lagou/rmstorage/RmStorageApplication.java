package com.lagou.rmstorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.lagou.rmstorage.repository")
@SpringBootApplication
public class RmStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmStorageApplication.class, args);
    }

}
