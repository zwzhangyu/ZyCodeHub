package com.gupaoedu.springcloud.gateway.springcloudgatewaysample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class GpDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<GpDefineGatewayFilterFactory.GpConfig>{

    public GpDefineGatewayFilterFactory(){
        super(GpConfig.class);
    }

    @Override
    public GatewayFilter apply(GpConfig config ) {
        return ((exchange, chain) -> {
            log.info("前置局部过滤器,name:"+config.getName());
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("后置局部过滤器");
            }));
        });
    }

    public static class GpConfig{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
