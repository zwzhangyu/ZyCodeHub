package com.zy.spring.mvc.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RequestHeader 注解使用
 *
 * @author zhangyu
 * @date 2022/11/19
 **/
@RestController
@RequestMapping("/annotation")
public class RequestHeaderTest {

    @GetMapping("/requestHeader")
    public Object requestHeader(@RequestHeader("userName") String userName) {
        return userName;
    }


}
