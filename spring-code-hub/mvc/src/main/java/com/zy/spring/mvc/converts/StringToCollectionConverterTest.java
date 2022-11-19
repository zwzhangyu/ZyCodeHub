package com.zy.spring.mvc.converts;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/converts")
public class StringToCollectionConverterTest {

    @GetMapping("/list")
    public Object convertList(@RequestParam("userList") List<Long> userList) {
        return userList;
    }

}
