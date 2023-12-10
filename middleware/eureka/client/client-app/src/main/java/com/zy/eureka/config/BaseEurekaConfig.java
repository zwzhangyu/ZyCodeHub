package com.zy.eureka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BaseEurekaConfig extends EurekaClientConfigBean {

    @Override
    public boolean shouldRegisterWithEureka() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows") || osName.contains("mac"))
            return false;
        return super.shouldRegisterWithEureka();
    }
}