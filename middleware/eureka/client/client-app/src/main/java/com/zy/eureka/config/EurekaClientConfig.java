package com.zy.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaClientConfig {

    @Bean
    public void configureEurekaClient() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.toLowerCase().contains("windows") || osName.toLowerCase().contains("mac")) {
            System.setProperty("eureka.client.register-with-eureka", "false");
            System.setProperty("eureka.client.fetch-registry", "false");
        }
    }
}
