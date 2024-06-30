package com.zy.spring;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    // 在 UserController Bean 中定义版本号
    private String version = "v1";

    public String getVersion() {
        return version;
    }

    @PostMapping("/delete")
    @RequestLog("删除用户，Version = {{ @userController.version }}，用户ID={{ #id }}，UserAgent={{ #request.getHeader('User-Agent') }}")
    public String delete (HttpServletRequest request,
                          @RequestParam("id") Long id) {
        // TODO 根据 id 删除 用户
        return "ok";
    }
}