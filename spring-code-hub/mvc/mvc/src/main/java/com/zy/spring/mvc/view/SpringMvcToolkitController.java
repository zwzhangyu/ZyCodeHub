package com.zy.spring.mvc.view;

import com.zy.spring.mvc.toolkit.SpringMvcToolkit;
import org.springframework.web.bind.annotation.*;

/*******************************************************
 * Created by ZhangYu on 2024/6/29
 * Description :
 * History   :
 *******************************************************/
@RestController
public class SpringMvcToolkitController {

    @GetMapping("/getUrlMethodInfo")
    public String getUrlMethodInfo(@RequestParam String url) {
        SpringMvcToolkit.getUrlMethodInfo(url);
        return "ok";
    }

    @RequestMapping(value = "/listAllHandlerMethods",method = RequestMethod.GET)
    public String listAllHandlerMethods() {
        SpringMvcToolkit.listAllHandlerMethods();
        return "ok";
    }
}
