package com.zy.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/methodTimeAgent")
public class MethodTimeController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(2000);
        return "OK";
    }
}
