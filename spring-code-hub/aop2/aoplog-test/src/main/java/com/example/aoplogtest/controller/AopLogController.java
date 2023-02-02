package com.example.aoplogtest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class AopLogController {

    @GetMapping("/index")
    public  String index(String name){
        System.out.println(name);
        return "Hello"+name;
    }
}
