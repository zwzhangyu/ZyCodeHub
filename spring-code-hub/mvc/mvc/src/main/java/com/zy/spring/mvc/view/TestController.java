package com.zy.spring.mvc.view;

import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*******************************************************
 * Created by ZhangYu on 2024/6/26
 * Description :
 * History   :
 *******************************************************/
@RestController
public class TestController {

    @PostMapping("/test")
    public Object test(HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        System.out.println(data);
        response.setHeader("t-head", "111");
        data.put("name", "1111");
        return data;
    }

}
