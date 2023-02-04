package com.zy.testdatagenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
