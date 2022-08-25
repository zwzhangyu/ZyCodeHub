package com.zy.aop;

import com.zy.aop.example2.User;
import com.zy.aop.example2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MySpringBootApplication {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);

    }


    @GetMapping("/example2")
    public String example2() {
        userService.printUser(new User("This is user"), "Hello");
        return "OK";
    }


}
