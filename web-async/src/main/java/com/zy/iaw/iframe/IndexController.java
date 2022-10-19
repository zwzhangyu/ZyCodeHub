package com.zy.iaw.iframe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/demo1")
    public String demo1() {
        return "iframe/demo1";
    }
}
