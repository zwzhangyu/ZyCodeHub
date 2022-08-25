package com.example.springboot.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IoCTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.close();
    }
}