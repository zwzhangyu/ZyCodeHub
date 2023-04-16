package com.example.es;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("com.example.es.mapper")
public class EeUseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EeUseApplication.class, args);
    }

}
