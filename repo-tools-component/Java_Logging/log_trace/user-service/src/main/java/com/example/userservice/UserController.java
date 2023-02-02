package com.example.userservice;

import com.example.common.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/user")
    public String a(String name) {
        log.info("Hello, " + name);
        try {
            //调用订单服务
            String result = HttpUtils.get("http://localhost:8082/order");
            //执行异步任务
            //测试通过 @Async注解异步线程
            userService.sendMsgByAsy();
            //测试线程池开启异步线程
            userService.sendMsgByThreadPool();
            return  result;
        } catch (Exception e) {
            log.error("远程调用异常", e);
        }
        return "fail";
    }
}