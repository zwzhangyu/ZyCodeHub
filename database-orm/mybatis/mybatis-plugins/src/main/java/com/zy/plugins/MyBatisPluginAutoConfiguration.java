//package com.zy.plugins;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConditionalOnWebApplication
//@ConditionalOnClass({PrintExceptionSqlInterceptor.class})
//@ConditionalOnProperty(prefix = "mybatis-plugin", name = "enabled", havingValue = "true", matchIfMissing = true)
//public class MyBatisPluginAutoConfiguration {
//
//    @Bean
//    public PrintExceptionSqlInterceptor yourInterceptor() {
//        return new PrintExceptionSqlInterceptor();
//    }
//}