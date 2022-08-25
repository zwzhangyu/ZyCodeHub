package com.example.springboot.ioc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.springboot.ioc")
public class AppConfig {
    @Bean
    public User initUser() {
        User user = new User();
        user.setId(10000L);
        user.setName("测试");
        return user;
    }
}
