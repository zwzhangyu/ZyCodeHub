package com.zy.iaw.iframe;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/iframe/example1")
public class IframeExample1Controller {

    @GetMapping("/index")
    public String index() {
        return "iframe/example1/index";
    }


    @GetMapping("/showStr")
    public String showStr(Model model) {
        model.addAttribute("random", RandomUtil.randomString(5));
        return "iframe/example1/str";
    }


    @GetMapping("/showNum")
    public String showNum(Model model) {
        model.addAttribute("random", RandomUtil.randomNumbers(5));
        return "iframe/example1/num";
    }

}
