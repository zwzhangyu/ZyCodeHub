package com.zy.spring.spring_retry.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * spring-retry 重试框架测试
 *
 * @author zhangyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/retry")
    public ResponseEntity retry() {
        userService.getUser("test");
        return ResponseEntity.ok().build();
    }
}
