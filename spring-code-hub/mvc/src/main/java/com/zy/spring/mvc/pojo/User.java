package com.zy.spring.mvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    private String name;

    private String phone;


    public static List<User> list() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "李四2", "1212121"));
        userList.add(new User(2L, "李四3", "4343"));
        userList.add(new User(3L, "李四4", "4343"));
        return userList;
    }


}
