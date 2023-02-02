package com.gupaoedu.springboot.gateway.springcloudgatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/say")
    public String sayHello(){
        return "HelloWorld";
    }
}
