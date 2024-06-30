package com.zy.spring.mvc.view;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*******************************************************
 * Created by ZhangYu on 2024/6/26
 * Description :
 * History   :
 *******************************************************/
@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody Map<String,Object> data){
        System.out.println(data);
        return "ok";
    }

}
