package com.zy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringSpELApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSpELApplication.class, args);
    }

}
