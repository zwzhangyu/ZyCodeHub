package com.example.springboot;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/liveReload")
@RestController
public class LiveReloadExample {

    private String name = "WWW  Ali1122244433444433334666vvv5554344221111c666e";


    @GetMapping("/hello")
    public String hello() {
        System.out.println("dd2d");
        return name;
    }

}
